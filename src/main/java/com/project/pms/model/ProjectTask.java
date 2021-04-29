package com.project.pms.model;

import java.util.Objects;

public class ProjectTask {
    Long projectId;
    Long taskId;

    public ProjectTask() {
    }

    public ProjectTask(Long projectId, Long taskId) {
        this.projectId = projectId;
        this.taskId = taskId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTask that = (ProjectTask) o;
        return Objects.equals(projectId, that.projectId) && Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, taskId);
    }

    @Override
    public String toString() {
        return "ProjectTask{" +
                "projectId=" + projectId +
                ", taskId=" + taskId +
                '}';
    }
}
