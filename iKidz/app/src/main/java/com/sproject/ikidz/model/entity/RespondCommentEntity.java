package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespondCommentEntity {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("created_at")
@Expose
private Long createdAt;
@SerializedName("status")
@Expose
private String status;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Long getCreatedAt() {
return createdAt;
}

public void setCreatedAt(Long createdAt) {
this.createdAt = createdAt;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

}