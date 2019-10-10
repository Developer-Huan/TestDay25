package com.example.service.impl;

import com.example.dao.DeptDao;
import com.example.dao.EmpDao;
import com.example.dao.impl.DeptDaoImpl;
import com.example.dao.impl.EmpDaoImpl;
import com.example.entity.Dept;
import com.example.entity.Emp;
import com.example.entity.PageBean;
import com.example.entity.QuerySome;
import com.example.service.EmpService;

import java.util.List;

/**
 * @Author: 刘欢
 * @Date: 2019/9/30 8:10
 */
public class EmpServiceImpl implements EmpService {
    EmpDao empDao = new EmpDaoImpl();
    DeptDao deptDao = new DeptDaoImpl();

    @Override
    public List<Emp> queryBySome(String ename, String startTime, String endTime, String did) {
        return empDao.queryBySome(ename,startTime,endTime,did);
    }

    @Override
    public List<Dept> queryNameByDept() {
        return deptDao.queryName();
    }

    @Override
    public int insertEmp(Emp emp) {
        return empDao.insertEmp(emp);
    }

    @Override
    public Emp queryOneLine(String eid) {
        return empDao.queryOneLine(eid);
    }

    @Override
    public int updateEmp(Emp emp) {
        return empDao.updateEmp(emp);
    }

    @Override
    public int deleteEmp(String eid) {
        return empDao.deleteEmp(eid);
    }

    @Override
    public int batchDelEmp(String[] eids) {
        return empDao.batchDelEmp(eids);
    }

    @Override
    public PageBean getPageBean(QuerySome qs, Integer pageNow) {
        //下面开始将数据封装到PageBean
        PageBean pb = new PageBean();
        //1,先设置pageSize
        pb.setPageSize(5);
        //2，再设置总的行数 这一步需要到数据库中查询
        int count = empDao.queryCount(qs);
        pb.setCount(count);
        //3，设置当前页面
        pb.setPageNow(pageNow);
        //4,将查询到的该页数据封装到实体类 又需要调用dao层
        List data = empDao.queryData(qs,pb.getPageNow(),pb.getPageSize());
        pb.setData(data);
        return pb;
    }
}
