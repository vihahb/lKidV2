package com.snq.ikidz.teacher.model.message;

import com.google.gson.annotations.Expose;

public class MessageView {

    @Expose
    private ChatMessageEntity lastMessage;
    @Expose
    private MetadataEntity metadata;
    @Expose
    private UserData user;

    public MessageView() {
    }

    public MessageView(ChatMessageEntity lastMessage, MetadataEntity metadata, UserData user) {
        this.lastMessage = lastMessage;
        this.metadata = metadata;
        this.user = user;
    }

    public ChatMessageEntity getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(ChatMessageEntity lastMessage) {
        this.lastMessage = lastMessage;
    }

    public MetadataEntity getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataEntity metadata) {
        this.metadata = metadata;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MessageView{" +
                "lastMessage=" + lastMessage +
                ", metadata=" + metadata +
                ", user=" + user +
                '}';
    }
}
