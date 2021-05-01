package com.project.pms.dao;

import com.project.pms.model.Project;

public interface ProjectDAO extends DAO<Project>{
    Project getByTaskId(Long id);
}
