package com.project.pms.model;

public enum Status {
    NOT_STARTED(1L, "Not started"),
    PROCESS(2L, "In process"),
    ACCOMPLISHED(3L, "Accomplished"),
    DELAYED(4L, "Delayed");

    private Long id;
    private String status;


    Status(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }

    public static Status getById(Long id) {
        for(Status s : values()) {
            if(s.id.equals(id)) return s;
        }
        return null;
    }

    public static Status getByStatusName(String name) {
        for(Status s : values()) {
            if(s.getStatus().equals(name)) return s;
        }
        return null;
    }
}
