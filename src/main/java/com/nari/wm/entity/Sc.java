package com.nari.wm.entity;

import java.io.Serializable;

/**
 * (Sc)实体类
 *
 * @author makejava
 * @since 2021-01-20 18:45:18
 */
public class Sc implements Serializable {
    private static final long serialVersionUID = 724780148883884793L;

    private String sid;

    private String cid;

    private Double score;


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Sc{" +
                "sid='" + sid + '\'' +
                ", cid='" + cid + '\'' +
                ", score=" + score +
                '}';
    }
}