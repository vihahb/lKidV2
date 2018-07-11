package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValueSleepEntity {

@SerializedName("start_time")
@Expose
private String startTime;
@SerializedName("end_time")
@Expose
private String endTime;
@SerializedName("full_name")
@Expose
private String fullName;
@SerializedName("note")
@Expose
private String note;
@SerializedName("student_id")
@Expose
private Integer studentId;

    public ValueSleepEntity(String startTime, String endTime, String fullName, String note, Integer studentId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.fullName = fullName;
        this.note = note;
        this.studentId = studentId;
    }

    public String getStartTime() {
return startTime;
}

public void setStartTime(String startTime) {
this.startTime = startTime;
}

public String getEndTime() {
return endTime;
}

public void setEndTime(String endTime) {
this.endTime = endTime;
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

public Integer getStudentId() {
return studentId;
}

public void setStudentId(Integer studentId) {
this.studentId = studentId;
}

}