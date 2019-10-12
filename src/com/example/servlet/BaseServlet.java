package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 定义该类的目的，
 * 使用反射调用目的方法 降低耦合 这样每个继承于该类的子类只需要传target参数就可以调用他自己类中对应的方法 使用简单化
 * 要求：
 * 1,子类使用target作为方法的访问条件，target和方法名统一
 * 2,子类不能重写doPost 和 doGet 方法
 * @Author: 刘欢
 * @Date: 2019/10/12 14:03
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    /**
     * 该方法适用于所有继承于该类的servlet，前提是他们前台也是使用target来访问方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取target 由于被之类继承 所以这里获取到的target就是传到子类的request中的target
        String  target = request.getParameter("target");
        //接下来使用反射获取方法,当存在继承时 this表示调用这个方法的子类对象
        Class clazz = this.getClass();
        try {
            //根据传来的target去找方法名 所以编程时需要注意target的值和方法名的统一
            //传参 方法名 + 各参数类对应的class对象
            Method method = clazz.getDeclaredMethod(target,HttpServletRequest.class,HttpServletResponse.class);
            //执行该方法 参数： 执行该方法的类对象 + 参数1 + 参数2 返回:原方法void返回null 其它则返回对应类型的向上转型Object
            Object invoke = method.invoke(clazz.newInstance(), request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
