package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RESPCheckDevices {

@SerializedName("device_id")
@Expose
private String deviceId;
@SerializedName("device_token")
@Expose
private String deviceToken;
@SerializedName("platform")
@Expose
private String platform;
@SerializedName("user_id")
@Expose
private Integer userId;
@SerializedName("type")
@Expose
private String type;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("id")
@Expose
private Integer id;

public String getDeviceId() {
return deviceId;
}

public void setDeviceId(String deviceId) {
this.deviceId = deviceId;
}

public String getDeviceToken() {
return deviceToken;
}

public void setDeviceToken(String deviceToken) {
this.deviceToken = deviceToken;
}

public String getPlatform() {
return platform;
}

public void setPlatform(String platform) {
this.platform = platform;
}

public Integer getUserId() {
return userId;
}

public void setUserId(Integer userId) {
this.userId = userId;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

}