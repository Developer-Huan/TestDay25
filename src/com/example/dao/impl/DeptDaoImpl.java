package com.example.dao.impl;

import com.example.dao.DeptDao;
import com.example.entity.Dept;
import com.example.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: 刘欢
 * @Date: 2019/10/8 8:15
 */
public class DeptDaoImpl implements DeptDao {
    QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

    @Override
    public List<Dept> queryName() {
        String sql = " SELECT * FROM dept ";
        try {
            return qr.query(sql,new BeanListHandler<>(Dept.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
