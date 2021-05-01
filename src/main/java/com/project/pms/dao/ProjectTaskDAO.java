package com.project.pms.dao;

import com.project.pms.model.Project;
import com.project.pms.model.ProjectTask;
import com.project.pms.model.Task;

import java.util.Collection;

public interface ProjectTaskDAO extends DAO<ProjectTask> {
    ProjectTask getProjectByTaskId(Long id);
    Collection<ProjectTask> getTasksByProjectId(Long id);
    boolean deleteByProjectId(Project project);
    boolean deleteByTaskId(Task task);
}
