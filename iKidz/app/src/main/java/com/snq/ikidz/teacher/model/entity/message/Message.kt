package com.snq.ikidz.teacher.model.entity.message

import com.google.firebase.database.DataSnapshot
import java.util.*

class Message {
    var avatar: String? = null
    var name: String? = null
    var message: String? = null
    var time: String? = null

    var id: String = ""
    var userId: String = ""
    var date: Date? = null

    constructor(data: DataSnapshot) {
        if (!data.exists()) {
            return
        }
        id = data.key
        val dict = data.value as HashMap<String, Any>
        userId = dict["userId"] as? String ?: ""
        message = dict["message"] as? String ?: ""
        val timestamp = dict["timestamp"] as? Long ?: 0
        if (timestamp > 0) {
            date = Date(timestamp)
        }

    }


    constructor(roomId: String, message: String) {
        this.id = FirebaseService.getRoomMessageRef().child(roomId).push().key
        this.avatar = User.current!!.avatar
        this.name = User.current!!.person!!.fullName
        this.date = Date()
        this.message = message
        this.userId = User.current?.fbUser?.id ?: ""
    }

    fun value(): HashMap<String, Any> {
        var hashMap = HashMap<String, Any>()
        hashMap["avatar"] = this.avatar ?: ""
        hashMap["message"] = this.message ?: ""
        hashMap["userId"] = this.userId
        hashMap["timestamp"] = this.date?.time ?: Date().time
        hashMap["name"] = this.name ?: User.current!!.person!!.fullName
        return hashMap
    }
}