package com.hwj.service;

import java.util.List;

/**
 * @author houwenjing
 * @description
 * @date 2021/7/15 11:30 上午
 */
public interface BaseService<T>{
    boolean remove(Integer id);
    boolean remove(List<Integer> ids);
    boolean save(T bean);
    T get(Integer id);
    List<T> list();
    Integer count();
}
