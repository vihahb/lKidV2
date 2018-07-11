package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateSleepAndOtherEntity {

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
    private List<ValueSleepEntity> activities;

    public UpdateSleepAndOtherEntity(String type, String activityDate, Integer classId, List<ValueSleepEntity> activities) {
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

    public List<ValueSleepEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ValueSleepEntity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "UpdateSleepAndOtherEntity{" +
                "type='" + type + '\'' +
                ", activityDate='" + activityDate + '\'' +
                ", classId=" + classId +
                ", activities=" + activities +
                '}';
    }
}
