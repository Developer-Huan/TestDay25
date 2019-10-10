package com.example.dao;

import com.example.entity.Dept;

import java.util.List;

/**
 * @Author: 刘欢
 * @Date: 2019/10/8 8:08
 */
public interface DeptDao {

    /**
     * 查询出dept表的所有部门名称
     * @return 结果集
     */
    List<Dept> queryName();
}
