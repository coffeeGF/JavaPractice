package com.hwj.bean;

import java.util.Date;

/**
 * @author houwenjing
 * @description
 * @date 2021/8/11 6:52 下午
 */
public class Award extends BaseBean{
    private  String name;
    private  String image;
    private  String intro;

    public Award(Integer id, Date createTime, String name, String image, String intro) {
        super(id, createTime);
        this.name = name;
        this.image = image;
        this.intro = intro;
    }

    public Award() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
