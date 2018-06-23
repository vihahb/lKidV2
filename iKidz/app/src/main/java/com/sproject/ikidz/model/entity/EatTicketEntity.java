package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EatTicketEntity {

@SerializedName("birthday")
@Expose
private String birthday;
@SerializedName("is_register")
@Expose
private String isRegister;
@SerializedName("full_name_student")
@Expose
private String fullNameStudent;
@SerializedName("avatar_student")
@Expose
private String avatarStudent;

public String getBirthday() {
return birthday;
}

public void setBirthday(String birthday) {
this.birthday = birthday;
}

public String getIsRegister() {
return isRegister;
}

public void setIsRegister(String isRegister) {
this.isRegister = isRegister;
}

public String getFullNameStudent() {
return fullNameStudent;
}

public void setFullNameStudent(String fullNameStudent) {
this.fullNameStudent = fullNameStudent;
}

public String getAvatarStudent() {
return avatarStudent;
}

public void setAvatarStudent(String avatarStudent) {
this.avatarStudent = avatarStudent;
}

}