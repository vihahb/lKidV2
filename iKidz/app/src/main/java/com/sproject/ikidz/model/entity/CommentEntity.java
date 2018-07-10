package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentEntity {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("comment")
@Expose
private String comment;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("user_id")
@Expose
private Integer userId;
@SerializedName("full_name")
@Expose
private String fullName;
@SerializedName("avatar")
@Expose
private String avatar;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getComment() {
return comment;
}

public void setComment(String comment) {
this.comment = comment;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public Integer getUserId() {
return userId;
}

public void setUserId(Integer userId) {
this.userId = userId;
}

public String getFullName() {
return fullName;
}

public void setFullName(String fullName) {
this.fullName = fullName;
}

public String getAvatar() {
return avatar;
}

public void setAvatar(String avatar) {
this.avatar = avatar;
}

}