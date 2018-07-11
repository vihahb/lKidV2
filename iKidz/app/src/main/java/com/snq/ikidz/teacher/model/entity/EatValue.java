package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EatValue {

@SerializedName("student_id")
@Expose
private Integer studentId;
@SerializedName("full_name")
@Expose
private String fullName;
@SerializedName("note")
@Expose
private String note;
@SerializedName("eat_status")
@Expose
private String eatStatus;
@SerializedName("activity_name")
@Expose
private String activityName;

    public EatValue(Integer studentId, String fullName, String note, String eatStatus, String activityName) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.note = note;
        this.eatStatus = eatStatus;
        this.activityName = activityName;
    }

    public Integer getStudentId() {
return studentId;
}

public void setStudentId(Integer studentId) {
this.studentId = studentId;
}

public String getFullName() {
return fullName;
}

public void setFullName(String fullName) {
this.fullName = fullName;
}

public String getNote() {
return note;
}

public void setNote(String note) {
this.note = note;
}

public String getEatStatus() {
return eatStatus;
}

public void setEatStatus(String eatStatus) {
this.eatStatus = eatStatus;
}

public String getActivityName() {
return activityName;
}

public void setActivityName(String activityName) {
this.activityName = activityName;
}

}