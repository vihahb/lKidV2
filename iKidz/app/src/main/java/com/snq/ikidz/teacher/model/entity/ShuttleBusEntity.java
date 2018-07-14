package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShuttleBusEntity {

    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("pickup_point_id")
    @Expose
    private String pickupPointId;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("type_pickup")
    @Expose
    private String typePickup;
    @SerializedName("avatar_student")
    @Expose
    private String avatarStudent;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPickupPointId() {
        return pickupPointId;
    }

    public void setPickupPointId(String pickupPointId) {
        this.pickupPointId = pickupPointId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTypePickup() {
        return typePickup;
    }

    public void setTypePickup(String typePickup) {
        this.typePickup = typePickup;
    }

    public String getAvatarStudent() {
        return avatarStudent;
    }

    public void setAvatarStudent(String avatarStudent) {
        this.avatarStudent = avatarStudent;
    }

}