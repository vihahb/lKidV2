package com.snq.teacher.model.message;

import com.google.gson.annotations.Expose;

public class ChatMessageEntity {
    @Expose
    public String message;
    @Expose
    public String name;
    @Expose
    public Long timestamp;
    @Expose
    public String type;
    @Expose
    public String userAvatar;
    @Expose
    public String userId;
    @Expose
    public String userIds;

    public ChatMessageEntity() {
    }

    public ChatMessageEntity(String message, String name, Long timestamp, String type, String userAvatar, String userId, String userIds) {
        this.message = message;
        this.name = name;
        this.timestamp = timestamp;
        this.type = type;
        this.userAvatar = userAvatar;
        this.userId = userId;
        this.userIds = userIds;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "ChatMessageEntity{" +
                "message='" + message + '\'' +
                ", name='" + name + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", userId='" + userId + '\'' +
                ", userIds='" + userIds + '\'' +
                '}';
    }
}
