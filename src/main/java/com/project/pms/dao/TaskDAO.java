package com.project.pms.dao;

import com.project.pms.model.Task;

import java.util.List;

public interface TaskDAO extends DAO<Task> {
    List<Task> getByProjectId(Long id);
}
