package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataProfilePostEntify {

    @SerializedName("class_id")
    @Expose
    private Integer classId;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("schoolReports")
    @Expose
    private List<ProfilePostEntify> schoolReports;

    public List<ProfilePostEntify> getSchoolReports() {
        return schoolReports;
    }

    public void setSchoolReports(List<ProfilePostEntify> schoolReports) {
        this.schoolReports = schoolReports;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

}