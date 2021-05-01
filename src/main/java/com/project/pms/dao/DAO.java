package com.project.pms.dao;

import com.project.pms.model.ProjectTask;
import com.project.pms.response.TaskResponse;

import java.util.Collection;

public interface DAO<T> {
    Collection<T> getAll();
    T getById(Long id);
    boolean update(T t);
    Long create(T t);
    boolean delete(T t);
}
