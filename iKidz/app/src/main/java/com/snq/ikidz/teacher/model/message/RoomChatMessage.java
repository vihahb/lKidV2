package com.snq.ikidz.teacher.model.message;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class RoomChatMessage {
    public ArrayList<ChatMessageEntity> message;

    public RoomChatMessage() {
    }


    public RoomChatMessage(Iterable<DataSnapshot> children) {
        message = new ArrayList<>();
        for (DataSnapshot entity:children) {
            ChatMessageEntity chat = entity.getValue(ChatMessageEntity.class);
            message.add(chat);
            Log.e("Childs: ", " - " + chat.toString());
        }
    }

    public ArrayList<ChatMessageEntity> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<ChatMessageEntity> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RoomChatMessage{" +
                "message=" + message +
                '}';
    }
}
