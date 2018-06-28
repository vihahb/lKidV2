package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentLearnEntity {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("full_name")
@Expose
private String fullName;
@SerializedName("review_study")
@Expose
private String reviewStudy;
@SerializedName("birthday")
@Expose
private String birthday;
@SerializedName("title_1")
@Expose
private String title1;
@SerializedName("title_2")
@Expose
private String title2;
@SerializedName("avatar")
@Expose
private String avatar;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getFullName() {
return fullName;
}

public void setFullName(String fullName) {
this.fullName = fullName;
}

public String getReviewStudy() {
return reviewStudy;
}

public void setReviewStudy(String reviewStudy) {
this.reviewStudy = reviewStudy;
}

public String getBirthday() {
return birthday;
}

public void setBirthday(String birthday) {
this.birthday = birthday;
}

public String getTitle1() {
return title1;
}

public void setTitle1(String title1) {
this.title1 = title1;
}

public String getTitle2() {
return title2;
}

public void setTitle2(String title2) {
this.title2 = title2;
}

public String getAvatar() {
return avatar;
}

public void setAvatar(String avatar) {
this.avatar = avatar;
}

}