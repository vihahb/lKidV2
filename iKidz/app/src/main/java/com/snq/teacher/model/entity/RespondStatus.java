package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;

public class RespondStatus {
    @Expose
    private String status;

    public RespondStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RespondStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
