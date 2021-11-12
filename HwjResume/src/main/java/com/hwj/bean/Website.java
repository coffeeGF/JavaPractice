package com.hwj.bean;

import java.util.Date;

/**
 * @author houwenjing
 * @description
 * @date 2021/7/9 5:45 下午
 */
public class Website extends BaseBean{
    private String footer;

    public Website (){

    }

    public Website(Integer id, Date createTime, String footer) {
        super(id, createTime);
        this.footer = footer;
    }

    public Website(String footer) {
        this.footer = footer;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

}
