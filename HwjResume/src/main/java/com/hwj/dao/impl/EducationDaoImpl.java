package com.hwj.dao.impl;

import com.hwj.bean.Education;
import com.hwj.dao.EducationDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houwenjing
 * @description
 * @date 2021/7/9 5:48 下午
 */
public class EducationDaoImpl extends BaseDaoImpl<Education> implements EducationDao {
    /**
     * 列表
     *
     * @return 教育经验列表
     */
    public List<Education> list() {
        String querySql = "SELECT id, created_time, type, name, intro, begin_day, end_day FROM "+ table;
        return tpl.query(querySql, new BeanPropertyRowMapper<>(Education.class));
    }

    /**
     * 删除
     *
     * @return 是否保存成功
     */
    public boolean save(Education education) {
        Integer id = education.getId();
        List<Object> args = new ArrayList<>();
        args.add(education.getType());
        args.add(education.getName());
        args.add(education.getIntro());
        args.add(education.getBeginDay());
        args.add(education.getEndDay());
        String sql;
        if (id == null || id < 1) {
            sql = "INSERT INTO "+ table +" (type, name, intro, begin_day, end_day) values(?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE "+ table +" set type = ?, name = ?, intro = ?, begin_day = ?, end_day = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql, args.toArray()) > 0;
    }

    public String table() {
        return "education";
    }

    /**
     * 获取单个对象
     */
    public Education get(Integer id) {
        String sql = "SELECT id, created_time, name, type, intro, begin_day, end_day FROM "+ table +" WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Education.class), id);
    }


}
