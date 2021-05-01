package com.project.pms.response;

import com.project.pms.model.Status;

import java.util.Objects;

public class StatusResponse {

    private Long id;
    private Status status;
    private String name;

    public StatusResponse() {
    }

    public StatusResponse(Long id, Status status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusResponse that = (StatusResponse) o;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, name);
    }

    @Override
    public String toString() {
        return "StatusResponse{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                '}';
    }
}
