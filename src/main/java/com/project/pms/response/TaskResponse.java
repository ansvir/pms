package com.project.pms.response;


import java.util.Objects;

public class TaskResponse {
    private Long id;
    private String name;
    private Integer time;
    private String start;
    private String end;
    private Long statusId;
    private Long projectId;

    public TaskResponse() {
    }

    public TaskResponse(Long id, String name, Integer time, String start, String end, Long statusId, Long projectId) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.start = start;
        this.end = end;
        this.statusId = statusId;
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getStatus() {
        return this.statusId;
    }

    public void setStatus(Long statusId) {
        this.statusId = statusId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskResponse that = (TaskResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(time, that.time) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(statusId, that.statusId) && Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, time, start, end, statusId, projectId);
    }

    @Override
    public String toString() {
        return "TaskResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", statusId=" + statusId +
                ", projectId=" + projectId +
                '}';
    }
}
