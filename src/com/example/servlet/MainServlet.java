package com.example.servlet;

import com.example.entity.Dept;
import com.example.entity.Emp;
import com.example.entity.PageBean;
import com.example.entity.QuerySome;
import com.example.service.EmpService;
import com.example.service.impl.EmpServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将所有的servlet都写在一个了里面 将它们的功能分为不同的方法
 * @Author: 刘欢
 * @Date: 2019/9/29 19:45
 */
@WebServlet(urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    EmpService empService = new EmpServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String target = request.getParameter("target");
        switch(target){
            case "list":
                list(request,response);
                break;
            case "addEmp":
                addEmp(request,response);
                break;
            case "queryOneLine":
                queryOneLine(request,response);
                break;
            case "update":
                update(request,response);
                break;
            case "delete":
                delete(request,response);
                break;
            case "batchDel":
                batchDel(request,response);
                break;
            default:
                System.out.println("nothing");
        }
    }

    /**
     * 将所有用户的信息查询出来 ,并显示到用户信息界面上
     * @param request
     * @param response
     */
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //需要注意的是条件查询表单里面的标签的name不要和实体类的同名，因为如果同名 后续别的功能在转发过来request的时候是后出现功能不符合预期的情况的，
        // 比如更新后想显示全部 但是同名因为update的request里面有ename所以会造成只显示对修改的这一行的姓名模糊查询的现象
        String name = request.getParameter("name");
        String startTime =  request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String did = request.getParameter("s_did");
        //第一个参数
        QuerySome qs = new QuerySome(name,startTime,endTime,did);
        //第二个参数
        String s = request.getParameter("pageNow");
        int pageNow;
        if (s !=null && !s.isEmpty()){
            pageNow=Integer.valueOf(s);
        }else{
            //默认第一页
            pageNow = 1;
        }

        //这里得到的pb里面含有前台页面分页所需要的一切数据，所以直接在域中传过去
        PageBean pb = empService.getPageBean(qs,pageNow);
        //将结果集保存到request域并转发到jsp页面(主界面)
        request.setAttribute("pb",pb);

        //下拉框需要用到全部的部门，将其存放到session域里面
        List<Dept> deptList = empService.queryNameByDept();
        request.getSession().setAttribute("deptList",deptList);

        //将得到的参数设置到域对象中，用于回显
        request.setAttribute("qs",qs);

        request.getRequestDispatcher("/emp/main.jsp").forward(request,response);
    }

    private void addEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Emp emp = new Emp();
        try {
            //因为getParameterMap没法将date类型保存，即 得到的都是String类型的结果
            //所以需要手动添加转换器将String类型转化为Date类型
            //定义一个转换器类对象
            DateConverter dc = new DateConverter();
            //设置要被转换的类型
            dc.setPatterns(new String[]{"yyyy-MM-dd"});
            //注册转换器 同时设置要转换为的类型
            ConvertUtils.register(dc, Date.class);

            BeanUtils.populate(emp,map);
            System.out.println(emp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        EmpService empService = new EmpServiceImpl();
        int i = empService.insertEmp(emp);
        if (i > 0){
            //插入成功
            request.setAttribute("insert",true);
        }else{
            //插入失败
            request.setAttribute("insert",false);
        }
        //跳转到界面刷新查看结果,不能使用重定向，因为需要把request传过去用于弹窗的提示
        list(request,response);
        //request.getRequestDispatcher("/main?target=list").forward(request,response);
        //response.sendRedirect(request.getContextPath()+"/emp/index.jsp");
    }

    /**
     * 修改前先根据eid查询出要修改的行的信息显示到修改页面
     */
    private void queryOneLine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        Emp emp = empService.queryOneLine(eid);
        //将得到的那行数据传到修改界面
        request.setAttribute("emp",emp);
        //转发到update.jsp
        request.getRequestDispatcher("/emp/update.jsp").forward(request,response);
    }

    /**
     * 做对数据库更新的操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Emp emp = new Emp();
        try {
            //由于得到的是String类型 要设置一个转换器 将其转化为date类型
            //定义一个转换器类对象
            DateConverter dc = new DateConverter();
            //设置要被转换的类型
            dc.setPatterns(new String[]{"yyyy-MM-dd"});
            //注册转换器 同时设置要转换为的类型
            ConvertUtils.register(dc, Date.class);
            BeanUtils.populate(emp,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        int i = empService.updateEmp(emp);
        if (i > 0){
            request.setAttribute("update",true);
        }else{
            request.setAttribute("update",false);
        }
        list(request,response);
    }

    /**
     * 删除一行，根据传入的数据从数据库删除一行数据
     * 删除不需要界面，这里做的事件是：删除后刷新主界面并且弹框提示删除成功
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        int i = empService.deleteEmp(eid);
        if (i > 0){
            request.setAttribute("delete",true);
        }else{
            request.setAttribute("delete",false);
        }
        list(request,response);
    }
    private void batchDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] eids = request.getParameterValues("checkId");
        int num = empService.batchDelEmp(eids);
        if (num > 0){
            request.setAttribute("batchDel",true);
        }else{
            request.setAttribute("batchDel",false);
        }
        list(request,response);
    }
}

