package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataParamsAddLearnEntity {

    @SerializedName("activities")
    @Expose
    private List<ParamsAddLearnEntity> activities;
    @SerializedName("class_id")
    @Expose
    private Integer classId;
    @SerializedName("activity_date")
    @Expose
    private String activityDate;

    public DataParamsAddLearnEntity(List<ParamsAddLearnEntity> activities, Integer classId, String activityDate) {
        this.activities = activities;
        this.classId = classId;
        this.activityDate = activityDate;
    }

    public List<ParamsAddLearnEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ParamsAddLearnEntity> activities) {
        this.activities = activities;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

}