package com.hwj.dao.impl;

import com.hwj.bean.Website;
import com.hwj.dao.WebsiteDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houwenjing
 * @description
 * @date 2021/7/9 5:48 下午
 */
public  class WebsiteDaoImpl extends BaseDaoImpl<Website> implements WebsiteDao {
    /**
     * 列表
     * @return
     */
    public List<Website> list() {
        String querySql = "SELECT id, created_time, footer from website";
        return tpl.query(querySql,new BeanPropertyRowMapper<>(Website.class));
    }

    /**
     * 删除
     * @return
     */
    public boolean save(Website website) {
        Integer id = website.getId();
        List<Object> args = new ArrayList<>();
        args.add(website.getFooter());
        String sql ;
        if (id == null || id  < 1) {
            sql = "INSERT INTO "+ table +"(footer) values(?)";
        }else {
            sql = "UPDATE "+ table +" set footer = ? WHERE id = ?";
            args.add(id);
        }
       return tpl.update(sql, args.toArray()) > 0;
    }

    public String table() {
        return "website";
    }


    public Website get(Integer id) {
        return null;
    }
}
