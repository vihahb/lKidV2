package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;

public class UpToClassEntity {
    @Expose
    private int id;
    @Expose
    private String title;

    public UpToClassEntity(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "UpToClassEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
