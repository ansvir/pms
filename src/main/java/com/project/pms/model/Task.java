package com.project.pms.model;

import java.sql.Date;
import java.util.Objects;

public class Task {
    private Long id;
    private String name;
    private Integer time;
    private Date start;
    private Date end;
    private Status status;

    public Task() {
    }

    public Task(Long id, String name, Integer time, Date start, Date end, Status status) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.start = start;
        this.end = end;
        this.status = status;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(name, task.name) && Objects.equals(time, task.time) && Objects.equals(start, task.start) && Objects.equals(end, task.end) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, time, start, end, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", status=" + status +
                '}';
    }
}
