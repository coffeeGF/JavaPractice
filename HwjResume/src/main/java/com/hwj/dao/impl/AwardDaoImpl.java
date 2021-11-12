package com.hwj.dao.impl;

import com.hwj.bean.Award;
import com.hwj.dao.AwardDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houwenjing
 * @description
 * @date 2021/8/11 6:51 下午
 */
public class AwardDaoImpl extends BaseDaoImpl<Award> implements AwardDao {
    @Override
    public List<Award> list() {
        String querySql = "SELECT id, created_time, name, image, intro FROM award";
        return tpl.query(querySql, new BeanPropertyRowMapper<>(Award.class));
    }

    @Override
    public boolean save(Award award) {
        Integer id = award.getId();
        List<Object> args = new ArrayList<>();
        args.add(award.getName());
        args.add(award.getImage());
        args.add(award.getIntro());
        String sql;
        if (id == null || id < 1) {
            sql = "INSERT INTO "+ table +" (name, image, intro) values(?, ?, ?)";
        } else {
            sql = "UPDATE "+ table +" set name = ?, image = ?, intro = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql, args.toArray()) > 0;
    }

    @Override
    public Award get(Integer id) {
        String sql = "SELECT id, created_time, name, intro, image FROM "+ table +" WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Award.class), id);
    }

    @Override
    public String table() {
        return "award";
    }
}
