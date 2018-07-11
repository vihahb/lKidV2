package com.snq.teacher.model.entity.message

import com.google.firebase.database.DataSnapshot
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.SharedUtils
import com.snq.teacher.sdk.callback.Callback
import java.io.Serializable
import java.util.*

class MessageRoom : Serializable {
    var id: String = ""
    var name: String = ""
    var avatar: String = ""
    var messages = mutableListOf<Message>()
    var type: RoomType = RoomType.unknown
    var createdDate: Date? = null

    var fbUsers = mutableListOf<FBUser>()

    constructor(id: String) {
        this.id = id
    }

    constructor(id: String, users: MutableList<FBUser>, type: RoomType) {
        this.id = id
        this.name = ""
        this.avatar = ""
        this.fbUsers = users
        this.type = type
        this.createdDate = Date()
    }


    fun init(data: DataSnapshot) {
        if (data.value == null) {
            return
        }
        val value = data.value as HashMap<String, Any>

        name = value["name"] as? String ?: ""

        val type = value["type"] as? String ?: "unknow"
        when (type) {
            "private" -> this.type = RoomType.private
            "public" -> this.type = RoomType.public
            else -> {
                this.type = RoomType.unknown
            }
        }

        val timeInterval = value["createdAt"] as? Long ?: 0

        if (timeInterval > 0) {
            this.createdDate = Date(timeInterval)
        }

        if (!value.containsKey("authorizedUsers")) {
            return
        }

        val userData = value["authorizedUsers"] as HashMap<String, Any>

        for (id in userData.keys) {
            if (id == User.current?.fbUser?.id) {
                continue
            }
            FirebaseService.getUser(id, object : Callback {
                override fun onError(code: Int, message: String) {

                }

                override fun onSuccess(data: Any?) {
                    if (data is FBUser) {
                        avatar = data.avatarUrl
                        fbUsers.add(data)
                        //TODO: cheat để cập nhập room, cần phải @Synchronized ở chỗ này
//                        ChatBroadcast.send(ChatBroadcast.addedRoom)
                    }
                }
            })
        }
    }

    fun value(): HashMap<String, Any> {
        val hashMap = HashMap<String, Any>()
        hashMap["id"] = id
        hashMap["type"] = if (type == RoomType.private) "private" else "public"
        hashMap["name"] = name
        hashMap["createdAt"] = createdDate?.time ?: Date().time

        var authorizedUsers = HashMap<String, Any>()

        for (user in fbUsers) {
            authorizedUsers[user.id] = user.name
        }
        hashMap["authorizedUsers"] = authorizedUsers
        return hashMap
    }

    fun setMetadata() {
        FirebaseService.setRoomMeta(this)
    }

    fun getMetadata() {
        FirebaseService.getRoomMeta(this, null)
    }

    fun fetchMessage() {
        FirebaseService.observeMessageAdded(this, object : Callback {
            override fun onError(code: Int, message: String) {
                print("message error: " + message)
            }

            override fun onSuccess(data: Any?) {
                messages.add(data as Message)
            }
        })
    }

    companion object {

        fun initRoom(person: Person?): MessageRoom? {
            if (person == null) {
                return null
            }
            val schoolCode = SharedUtils.getInstance().getStringValue(Constants.SCHOOL_CODE)
            val userId = User.current?.userId ?: 0
            val teacherId = person.userId
            val roomId2 = "" + schoolCode + "_" + userId + "_" + schoolCode + "_" + teacherId
            val roomId1 = "" + schoolCode + "_" + teacherId + "_" + schoolCode + "_" + userId

            val currentFBUser = User.current?.fbUser ?: return null
            val rooms = currentFBUser.rooms
            val existRoom = rooms.firstOrNull { it.id == roomId1 || it.id == roomId2 }
            if (existRoom != null) {
                return existRoom
            }

            val roomId = if (userId < teacherId)
                ("" + schoolCode + "_" + userId + "_" + schoolCode + "_" + teacherId)
            else ("" + schoolCode + "_" + teacherId + "_" + schoolCode + "_" + userId)

            val fireBaseId = SharedUtils.getInstance().getStringValue(Constants.SCHOOL_CODE) + "_" + person.userId
            val fbUser = FBUser(fireBaseId, person.fullName, person.avatar)
            FirebaseService.updateUser(fbUser, null)

            var users = mutableListOf<FBUser>(fbUser, currentFBUser)

            val messageRoom = MessageRoom(roomId, users, RoomType.private)
            messageRoom.setMetadata()
            currentFBUser.addRoom(messageRoom)

            FirebaseService.addRoom(messageRoom, currentFBUser, null)
            FirebaseService.addRoom(messageRoom, fbUser, null)

            return messageRoom

        }
    }
}

enum class RoomType {
    private, public, unknown
}