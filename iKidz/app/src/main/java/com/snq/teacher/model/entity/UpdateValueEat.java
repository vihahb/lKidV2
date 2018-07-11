package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateValueEat {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("activity_date")
    @Expose
    private String activityDate;
    @SerializedName("class_id")
    @Expose
    private Integer classId;
    @SerializedName("activities")
    @Expose
    private List<EatValue> activities;

    public UpdateValueEat(String type, String activityDate, Integer classId, List<EatValue> activities) {
        this.type = type;
        this.activityDate = activityDate;
        this.classId = classId;
        this.activities = activities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public List<EatValue> getActivities() {
        return activities;
    }

    public void setActivities(List<EatValue> activities) {
        this.activities = activities;
    }

}