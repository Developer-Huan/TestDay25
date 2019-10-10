package com.example.entity;

/**
 * @Author: 刘欢
 * @Date: 2019/10/9 15:57
 */
public class QuerySome {
    private String name;
    private String startTime;
    private String endTime;
    private String did;

    public QuerySome() {
    }

    public QuerySome(String name, String startTime, String endTime, String did) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
