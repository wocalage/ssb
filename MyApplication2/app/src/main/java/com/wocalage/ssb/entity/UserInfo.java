package com.wocalage.ssb.entity;

/**
 * Created by jiaojian on 2017/5/21.
 */

public class UserInfo {

    private String uid = "";
    private String name = "";
    private String head = "";
    private String des = "";
    private int level;//傻逼值
    private int upDistance; //上升名次，正为上升，负为下降，0为维持
    private int likeNum;

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getUpDistance() {
        return upDistance;
    }

    public void setUpDistance(int upDistance) {
        this.upDistance = upDistance;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
