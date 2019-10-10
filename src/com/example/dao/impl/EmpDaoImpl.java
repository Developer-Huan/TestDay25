package com.example.dao.impl;

import com.example.dao.EmpDao;
import com.example.entity.Emp;
import com.example.entity.QuerySome;
import com.example.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 刘欢
 * @Date: 2019/9/29 22:50
 */
public class EmpDaoImpl implements EmpDao {
    QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
    @Override
    public List<Emp> queryBySome(String ename, String startTime, String endTime, String did) {

        String sql = " SELECT emp.*,dept.dname FROM emp LEFT JOIN dept ON emp.did=dept.did WHERE 0=0  ";
        List<String> list = new ArrayList<>();
        //姓名模糊查询
        if (ename != null&& !ename.isEmpty()){
            sql += " AND emp.ename like ? ";
            list.add("%"+ename+"%");
            System.out.println(ename);
        }
        //起始日期范围查询
        if (startTime!=null && !startTime.isEmpty()){
            sql += " AND emp.estartime > ? ";
            list.add(startTime);
        }
        //结束日期范围查询
        if(endTime!=null && !endTime.isEmpty()){
            sql += " AND emp.estartime < ? ";
            list.add(endTime);
        }

        //部门查询
        if (did!=null && !did.isEmpty()){
            sql += " AND emp.did=? ";
            list.add(did);
        }
        try {
            return qr.query(sql,new BeanListHandler<>(Emp.class),list.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insertEmp(Emp emp) {

        String sql = " INSERT INTO emp VALUES(?,?,?,?,?,?) ";
        Object[] objs = {null,emp.getEname(),emp.getEsex(),emp.getEstartime(),emp.getEpay(),emp.getDid()};
        try {
            return qr.update(sql,objs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Emp queryOneLine(String eid) {
        String sql = " SELECT emp.*,dept.dname FROM emp LEFT JOIN  dept ON emp.did=dept.did WHERE emp.eid=?  ";
        try {
            return qr.query(sql,new BeanHandler<>(Emp.class),eid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateEmp(Emp emp) {
        Object[] objs = {emp.getEname(),emp.getEsex(),emp.getEstartime(),emp.getEpay(),emp.getDid(),emp.getEid()};
        String sql = " UPDATE emp SET ename=?,esex=?,estartime=?,epay=?,did=? WHERE eid=? ";
        try {
            return qr.update(sql,objs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteEmp(String eid) {
        String sql = " DELETE FROM emp WHERE eid=? ";
        try {
            return qr.update(sql,eid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int batchDelEmp(String[] eids) {
        //当传来的时null时直接返回0，因为这一次要对传来的参数使用方法，所以加一个判断是否为空
        if (eids == null){
            return 0;
        }
        /*
        //删除方式一
        int sum=0;
        for (String eid : eids) {
            sum += deleteEmp(eid);
        }
        return sum;
         */

        //删除方式二 DBUtils里面的qr.batch()方法
        String sql = " DELETE FROM emp WHERE eid=? ";
        //由于qr.batch(String sql,String[删除的次数][每次的缺省值] params)
        //定义一个二维数组 按照要求的格式传入数据

        Object[][] params = new Object[eids.length][];
        for (int i = 0; i < params.length; i++) {
            params[i] = new Object[]{eids[i]};
        }

        try {
            //batch()方法返回的是没次删除时修改的行数的数组
            int[] batch = qr.batch(sql, params);
            System.out.println(Arrays.toString(batch));
            //当每次都是删除一行的时候改变的总行数就是batch.length,但严格来水应该是batch中没项的和
            return batch.length;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int queryCount(QuerySome qs) {
        String name = qs.getName();
        String startTime = qs.getStartTime();
        String endTime = qs.getEndTime();
        String did = qs.getDid();
        String sql = " SELECT count(1) FROM emp LEFT JOIN dept ON emp.did=dept.did WHERE 0=0  ";
        List<String> list = new ArrayList<>();
        //姓名模糊查询
        if (name != null&& !name.isEmpty()){
            sql += " AND emp.ename like ? ";
            list.add("%"+name+"%");
        }
        //起始日期范围查询
        if (startTime!=null && !startTime.isEmpty()){
            sql += " AND emp.estartime > ? ";
            list.add(startTime);
        }
        //结束日期范围查询
        if(endTime!=null && !endTime.isEmpty()){
            sql += " AND emp.estartime < ? ";
            list.add(endTime);
        }

        //部门查询
        if (did!=null && !did.isEmpty()){
            sql += " AND emp.did=? ";
            list.add(did);
        }
        try {
            Long l = qr.query(sql, new ScalarHandler<Long>(), list.toArray());
            return  l.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List queryData(QuerySome qs, Integer pageNow, Integer pageSize) {
        String name = qs.getName();
        String startTime = qs.getStartTime();
        String endTime = qs.getEndTime();
        String did = qs.getDid();
        String sql = " SELECT emp.*,dept.dname FROM emp LEFT JOIN dept ON emp.did=dept.did WHERE 0=0  ";
        List<Object> list = new ArrayList<>();
        //姓名模糊查询
        if (name != null&& !name.isEmpty()){
            sql += " AND emp.ename like ? ";
            list.add("%"+name+"%");
        }
        //起始日期范围查询
        if (startTime!=null && !startTime.isEmpty()){
            sql += " AND emp.estartime > ? ";
            list.add(startTime);
        }
        //结束日期范围查询
        if(endTime!=null && !endTime.isEmpty()){
            sql += " AND emp.estartime < ? ";
            list.add(endTime);
        }

        //部门查询
        if (did!=null && !did.isEmpty()){
            sql += " AND emp.did=? ";
            list.add(did);
        }
        //分页限制 limit在遇到 给定范围的数据 数据库中不存在时，会将该范围内存在的数据查询出来，解决了最后一页数据的显示问题
        sql += " LIMIT ?,? ";
        list.add((pageNow-1)*pageSize);
        list.add(pageSize);

        try {
            return qr.query(sql,new BeanListHandler<Emp>(Emp.class),list.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
