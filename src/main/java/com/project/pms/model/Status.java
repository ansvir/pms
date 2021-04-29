package com.project.pms.model;

public enum Status {
    NOT_STARTED("Not started"),
    PROCESS("In process"),
    ACCOMPLISHED("Accomplished"),
    DELAYED("Delayed");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
