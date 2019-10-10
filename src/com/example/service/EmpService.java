package com.example.service;

import com.example.entity.Dept;
import com.example.entity.Emp;
import com.example.entity.PageBean;
import com.example.entity.QuerySome;

import java.util.List;

/**
 * @Author: 刘欢
 * @Date: 2019/9/30 8:05
 */
public interface EmpService {

    /**
     * 调用dao层的同名方法实现需求
     *
     * @param ename
     * @param startTime
     * @param endTime
     * @param did
     * @return
     */
    List<Emp> queryBySome(String ename, String startTime, String endTime, String did);

    /**
     * 调用dao查询dept表的所有dname
     * @return
     */
    List<Dept> queryNameByDept();

    /**
     * 调用dao层的方法实现数据的插入操作
     * @param emp
     * @return
     */
    int insertEmp(Emp emp);

    /**
     * 调用dao层的方法
     * 通过eid查询出一行结果
     * @param eid
     * @return
     */
    Emp queryOneLine(String eid);

    /**
     * 调用dao层的方法更新emp表
    * @param emp 要更新为的数据
     * @return 更新的行数
     */
    int updateEmp(Emp emp);

    /**
     * 调用dao层的方法删除emp的指定某行
     * @param eid
     * @return
     */
    int deleteEmp(String eid);

    /**
     * 调用dao层方法
     * @param eids
     * @return
     */
    int batchDelEmp(String[] eids);

    /**
     * 将数据封装到PageBean 里面 返回给调用者
     * 流程控制
     * @param qs 查询条件
     * @param pageNow 要查询的页
     * @return
     */
    PageBean getPageBean(QuerySome qs, Integer pageNow);
}
