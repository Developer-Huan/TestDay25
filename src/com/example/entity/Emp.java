package com.example.entity;

import java.util.Date;

/**
 * @author liuhuan
 */
public class Emp {
    private Integer eid;
    private String ename;
    private String esex;
    //date使用java.util包里面的，不然在内省的时候会报类型不匹配错误
    private Date estartime ;
    private Double epay;
    private Integer did;
    private String dname;
    //private Dept dept = new Dept();

    public Emp(){}

    public Emp(Integer eid, String ename, String esex, Date estartime, Double epay, Integer did) {
        this.eid = eid;
        this.ename = ename;
        this.esex = esex;
        this.estartime = estartime;
        this.epay = epay;
        this.did = did;
    }

//    public Emp(Integer eid, String ename, String esex, Date estartime, Double epay, Integer did, Dept dept) {
//        this.eid = eid;
//        this.ename = ename;
//        this.esex = esex;
//        this.estartime = estartime;
//        this.epay = epay;
//        this.did = did;
//        this.dept = dept;
//    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEsex() {
        return esex;
    }

    public void setEsex(String esex) {
        this.esex = esex;
    }

    public Date getEstartime() {
        return estartime;
    }

    public void setEstartime(Date estartime) {
        this.estartime = estartime;
    }

    public Double getEpay() {
        return epay;
    }

    public void setEpay(Double epay) {
        this.epay = epay;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
    //    public Dept getDept() {
//        return dept;
//    }
//
//    public void setDept(Dept dept) {
//        this.dept = dept;
//    }

    @Override
    public String toString() {
        return "Emp{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", esex='" + esex + '\'' +
                ", estartime=" + estartime +
                ", epay=" + epay +
                ", did=" + did +
                ", dname='" + dname + '\'' +
                '}';
    }
}
