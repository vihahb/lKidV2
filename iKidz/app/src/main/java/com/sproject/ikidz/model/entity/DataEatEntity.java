package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

public class DataEatEntity {

    @Expose
    private List<EatEntity> activity;
    @Expose
    private List<String> status_eat;

    public List<EatEntity> getActivity() {
        return activity;
    }

    public void setActivity(List<EatEntity> activity) {
        this.activity = activity;
    }

    public List<String> getStatus_eat() {
        return status_eat;
    }

    public void setStatus_eat(List<String> status_eat) {
        this.status_eat = status_eat;
    }
}
