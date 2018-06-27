package com.sproject.ikidz.model.entity.message

import com.google.firebase.database.DataSnapshot
import com.sproject.ikidz.sdk.callback.Callback

class FBUser {
    var id: String = ""
    var name: String = ""
    var avatarUrl: String = ""
    var rooms = mutableListOf<MessageRoom>()

    constructor(data: DataSnapshot) {
        if (!data.exists()) {
            return
        }
        id = data.key
        val value = data.value as HashMap<String, Any>
        name = value["name"] as? String ?: ""
        avatarUrl = value["avatar"] as? String ?: ""
    }

    constructor(id: String, name: String, avatar: String) {
        this.id = id
        this.name = name
        avatarUrl = avatar
    }

    fun fetchRoom() {
        FirebaseService.observeRoomAdded(this, object : Callback {
            override fun onError(code: Int, message: String) {

            }

            override fun onSuccess(data: Any?) {
                val mroom = data as MessageRoom
                mroom.getMetadata()
                addRoom(mroom)
            }
        })
    }

    fun addRoom(room: MessageRoom) {

        if (rooms.firstOrNull { it.id == room.id } != null) {
            return
        }

        rooms.add(0, room)
        room.fetchMessage()
    }

    fun sort() {
        rooms.sortWith(Comparator<MessageRoom> { p0, p1 ->
            var lastMsg = p0?.messages?.lastOrNull()
            val time0 = if (lastMsg != null) (lastMsg.date?.time ?: 0) else (p0?.createdDate?.time
                    ?: 1)
            lastMsg = p1?.messages?.lastOrNull()
            val time1 = if (lastMsg != null) (lastMsg.date?.time ?: 0) else (p1?.createdDate?.time
                    ?: 1)
            if (time0 < time1) 1 else -1
        })
    }

    fun value(): HashMap<String, Any> {
        val hashMap = HashMap<String, Any>()
        hashMap["name"] = name
        hashMap["id"] = id
        hashMap["avatar"] = avatarUrl
        return hashMap
    }

}