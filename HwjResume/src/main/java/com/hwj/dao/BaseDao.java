package com.hwj.dao;

import java.util.List;

/**
 * @author houwenjing
 * @description
 * @date 2021/7/9 5:48 下午
 */
public interface BaseDao<T> {
    List<T> list() ;
    boolean save(T bean);
    T get(Integer id) ;
    boolean remove(List<Integer> ids);
    boolean remove(Integer id) ;
    String table();
    Integer count();

}
