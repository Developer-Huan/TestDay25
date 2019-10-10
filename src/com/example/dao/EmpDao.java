package com.example.dao;

import com.example.entity.Emp;
import com.example.entity.QuerySome;

import java.util.List;

/**
 * @Author: 刘欢
 * @Date: 2019/9/29 22:42
 */
public interface EmpDao {

    /**
     * 根据要求查询出所有信息
     * 姓名模糊查询
     * 日期范围查询
     * 部门查询
     * @param ename 模糊查询的姓名
     * @param startTime 起始日期
     * @param endTime 结束日期
     * @param did 部门id
     * @return
     */
    List<Emp> queryBySome(String ename, String startTime, String endTime, String did);

    /**
     * 将数据插入到emp表中
     * @param emp
     * @return
     */
    int insertEmp(Emp emp);

    /**
     * 根据传来的eid，查询出一行结果
     * @param eid 查询条件
     * @return 实体类
     */
    Emp queryOneLine(String eid);

    /**
     * 根据条件将指定行的指定列更新为指定值
     * @param emp
     * @return
     */
    int updateEmp(Emp emp);

    /**
     * 根据eid删除一行
     * @param eid 要删除的行的id
     * @return
     */
    int deleteEmp(String eid);

    /**
     * 批量删除数据
     * @param eids 要删除的行的eid
     * @return 删除的行数
     */
    int batchDelEmp(String[] eids);

    /**
     * 分页查询之
     * 查询emp表指定条件下的总行数
     * @param qs 条件
     * @return
     */
    int queryCount(QuerySome qs);

    /**
     * 分页查询之
     * 查询出当前页需要的数据
     * @param qs
     * @param pageNow
     * @param pageSize
     * @return
     */
    List queryData(QuerySome qs,Integer pageNow,Integer pageSize);
}
