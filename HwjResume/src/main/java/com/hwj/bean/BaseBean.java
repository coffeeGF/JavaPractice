package com.hwj.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

/**
 * @author houwenjing
 * @description
 * @date 2021/7/9 5:45 下午
 */
public class BaseBean {
    private Integer id;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BaseBean(Integer id, Date createTime) {
        this.id = id;
        this.createTime = createTime;
    }

    public BaseBean() {
    }

    @JsonIgnore
    public String getJson() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
           e.printStackTrace();
        }
        return json;
    }
}
