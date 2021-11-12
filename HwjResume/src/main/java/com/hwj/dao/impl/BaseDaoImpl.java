package com.hwj.dao.impl;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.hwj.dao.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author houwenjing
 * @description
 * @date 2021/7/9 5:48 下午
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    protected   static JdbcTemplate tpl;
    static {
        try (InputStream is = BaseDaoImpl.class.getClassLoader().getResourceAsStream("druid.properties");){
            Properties properties = new Properties();
            properties.load(is);
            DataSource ds = DruidDataSourceFactory.createDataSource(properties);
            tpl = new JdbcTemplate(ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected   String table = table();


    public  boolean remove(Integer id) {
        String sql = "DELETE FROM "+ table +" WHERE id = ?";
        return tpl.update(sql,id) > 0;
    }

    public boolean remove(List<Integer> ids) {
        List<Object> args = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(table).append(" WHERE id in (");
        for (Integer id : ids) {
            args.add(id);
            sql.append("?, ");
        }
        sql.replace(sql.length() - 2, sql.length(), ")");
        // DELETE FROM education WHERE id in (?, ?, ?)
        return tpl.update(sql.toString(), args.toArray()) > 0;
    }

    public  Integer count() {
        String sql = "SELECT COUNT(*) FROM " + table;
        return tpl.queryForObject(sql,new BeanPropertyRowMapper<>(Integer.class));
    }
}
