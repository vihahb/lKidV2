package com.snq.teacher.model.message.utils;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.snq.teacher.model.message.MetadataEntity;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseUtils {
    private static final String TAG = "FirebaseDatabaseUtils";

    private DatabaseReference room_message;
    private DatabaseReference room_metadata;
    private DatabaseReference room_username_online;


    private static MetadataEntity dataRoom;

    private FirebaseDatabaseUtils() {
    }

    public static MetadataEntity getRoomMetadata(String roomKey) {
        dataRoom = null;
        DatabaseReference metadataRoom = FirebaseDatabase.getInstance().getReference("room-metadata/" + roomKey);
        metadataRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataRoom = dataSnapshot.getValue(MetadataEntity.class);
                Log.e(TAG, "getRoomMetadata: " + dataRoom.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.e(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
        return dataRoom;
    }

    public static List<String> getRoomJoined(String user_id) {
        DatabaseReference roomJoined = FirebaseDatabase.getInstance().getReference("users/" + user_id + "/rooms");
        List<String> roomKey = new ArrayList<>();
        roomJoined.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    roomKey.add(child.getKey());
                    Log.e(TAG, "getRoomJoined: " + child.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.e(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
        return roomKey;
    }
}
