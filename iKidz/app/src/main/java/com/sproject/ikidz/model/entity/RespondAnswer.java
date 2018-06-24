package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;

public class RespondAnswer {

    @Expose
    private String status;
    @Expose
    private AnswerEntity data;

    public RespondAnswer(String status, AnswerEntity data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AnswerEntity getData() {
        return data;
    }

    public void setData(AnswerEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespondAnswer{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
