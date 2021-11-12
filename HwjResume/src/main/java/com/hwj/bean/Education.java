package com.hwj.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author houwenjing
 * @description
 * @date 2021/7/9 5:45 下午
 */
public class Education extends BaseBean{
    private String name;
    private Integer type;
    private String intro;
    private Date beginDay;
    private Date endDay;


    public Education(){

    }

    public Education(Integer id, Date createTime, String name, Integer type, String intro, Date beginDay, Date endDay) {
        super(id, createTime);
        this.name = name;
        this.type = type;
        this.intro = intro;
        this.beginDay = beginDay;
        this.endDay = endDay;
    }

    public Education(String name, Integer type, String intro, Date beginDay, Date endDay) {
        this.name = name;
        this.type = type;
        this.intro = intro;
        this.beginDay = beginDay;
        this.endDay = endDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(Date beginDay) {
        this.beginDay = beginDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    @JsonIgnore
    public  String getTypeString( ) {
        switch (type) {
            case 1:
                return "小学";
            case 2:
                return "中学";
            case 3:
                return "高中";
            case 4:
                return "中专";
            case 5:
                return "大专";
            case 6:
                return "本科";
        }
        return "其他";
    }
}
