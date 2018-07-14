package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassEntity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("class_code")
    @Expose
    private String classCode;
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("school_year_id")
    @Expose
    private Integer schoolYearId;
    @SerializedName("grade_id")
    @Expose
    private Integer gradeId;
    @SerializedName("firebase_id_class")
    @Expose
    private String firebaseIdClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(Integer schoolYearId) {
        this.schoolYearId = schoolYearId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getFirebaseIdClass() {
        return firebaseIdClass;
    }

    public void setFirebaseIdClass(String firebaseIdClass) {
        this.firebaseIdClass = firebaseIdClass;
    }

}