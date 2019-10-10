package com.example.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询的实体类
 * 该类封装传到界面的数据
 * @Author: 刘欢
 * @Date: 2019/10/8 10:15
 */
public class PageBean {

    /**
     * 要显示出来的数据总条数
     */
    private Integer count;

    /**
     * 当前是第几页
     */
    private Integer pageNow;

    /**
     * 每页数据条数
     */
    private Integer pageSize;

    /**
     * 页数
     */
    private Integer pageTotal;

    /**
     * 没次查询出来的数据
     */
    private List data;


    public PageBean() {
        //默认每页五行数据
        pageSize=5;
        //将data初始化
        data = new ArrayList();
    }

    /**
     * 设置每页要显示的行数
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 在pageSize知道的情况下，再设置count 其实可以把pageTotal也同时确认下来
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
        //为了保证最后一页数据不满pageSize时也显示,就要设置当除数有余的时候也将其算作一页
        if (count==0){
            //保证不会显示第0页的情况，至少都是第一页；
            pageTotal=1;
        }else if (count%pageSize==0){
            pageTotal = count/pageSize;
        }else{
            pageTotal = count/pageSize + 1;
        }
    }

    /**
     * 设置当前页 要通过代码去掉不合理的数据
     * @param pageNow
     */
    public void setPageNow(Integer pageNow) {
        if (pageNow <=0){
            pageNow=1;
        }
        if (pageNow > pageTotal){
            pageNow=pageTotal;
        }
        this.pageNow = pageNow;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getPageNow() {
        return pageNow;
    }



    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
