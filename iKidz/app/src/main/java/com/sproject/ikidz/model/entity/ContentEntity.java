package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentEntity {

    @SerializedName("content_morning")
    @Expose
    private String contentMorning;
    @SerializedName("content_afternoon")
    @Expose
    private String contentAfternoon;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("date")
    @Expose
    private String date;

    public String getContentMorning() {
        return contentMorning;
    }

    public void setContentMorning(String contentMorning) {
        this.contentMorning = contentMorning;
    }

    public String getContentAfternoon() {
        return contentAfternoon;
    }

    public void setContentAfternoon(String contentAfternoon) {
        this.contentAfternoon = contentAfternoon;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}