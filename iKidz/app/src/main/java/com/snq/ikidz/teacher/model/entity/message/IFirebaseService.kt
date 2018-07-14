package com.snq.ikidz.teacher.model.entity.message

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

interface IFirebaseService {
}

fun IFirebaseService.getDatabaseRef(): DatabaseReference {
    return FirebaseDatabase.getInstance().reference
}

fun IFirebaseService.getUserRef(): DatabaseReference {
    return this.getDatabaseRef().child("users")
}

fun IFirebaseService.getRoomMetaRef(): DatabaseReference {
    return this.getDatabaseRef().child("room-metadata")
}

fun IFirebaseService.getRoomMessageRef(): DatabaseReference {
    return this.getDatabaseRef().child("room-messages")
}
