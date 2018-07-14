package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;

public class RequestNews {
    @Expose
    private int type;
    @Expose
    private int class_id;

    public RequestNews(int type, int class_id) {
        this.type = type;
        this.class_id = class_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "RequestNews{" +
                "type=" + type +
                ", class_id=" + class_id +
                '}';
    }
}
