package com.project.pms.dao;

import com.project.pms.model.Project;

import java.util.Collection;
import java.util.List;

public interface DAO<T> {
    Collection<T> getAll();
    T getById(Long id);
    boolean update(T t);
    Long create(T t);
    boolean delete(T t);
}
