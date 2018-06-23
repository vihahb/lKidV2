package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;

public class PostParamsEat {
    @Expose
    private int class_id;
    @Expose
    private int month;
    @Expose
    private int year;

    public PostParamsEat(int class_id, int month, int year) {
        this.class_id = class_id;
        this.month = month;
        this.year = year;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "PostParamsEat{" +
                "class_id=" + class_id +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
