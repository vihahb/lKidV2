package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataWeek implements Serializable {
    @SerializedName("week")
    @Expose
    private String week;
    @SerializedName("data_week")
    @Expose
    private List<ContentEntity> data_week;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public List<ContentEntity> getData_week() {
        return data_week;
    }

    public void setData_week(List<ContentEntity> data_week) {
        this.data_week = data_week;
    }

    @Override
    public String toString() {
        return "DataWeek{" +
                "week='" + week + '\'' +
                ", data_week=" + data_week +
                '}';
    }
}
