package com.sproject.ikidz.model.entity.message

import android.util.Log
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sproject.ikidz.sdk.callback.Callback

object FirebaseService : IFirebaseService {

    var roomChildEventListener: ChildEventListener? = null
    var messageChildEventListener: ChildEventListener? = null

    fun observeRoomAdded(user: FBUser, callback: Callback): ChildEventListener {
        roomChildEventListener = this.getUserRef().child(user.id).child("rooms").addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                callback.onError(-1, p0.toString())
            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                val messageRoom = MessageRoom(p0!!.key)
                callback.onSuccess(messageRoom)
            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {

            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot?) {

            }
        })
        return roomChildEventListener!!
    }

    fun removeRoomObserve(user: FBUser) {
        if (roomChildEventListener != null) {
            this.getUserRef().child(user.id).removeEventListener(roomChildEventListener)
        }
    }

    fun observeMessageAdded(room: MessageRoom, callback: Callback) {
        messageChildEventListener = this.getRoomMessageRef().child(room.id).orderByChild("timestamp").addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                callback.onError(-1, p0.toString())
            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                callback.onSuccess(Message(p0!!))
//                ChatBroadcast.send(ChatBroadcast.addedMessage)
            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {

            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot?) {

            }
        })
    }

    fun setRoomMeta(room: MessageRoom) {
        if (room.id.isBlank()) {
            return
        }
        this.getRoomMetaRef().child(room.id).setValue(room.value())
    }

    fun getRoomMeta(room: MessageRoom, callback: Callback?) {
        this.getRoomMetaRef().child(room.id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot?) {
                if (p0?.value != null) {
                    room.init(p0)
                    callback?.onSuccess(room)
                    Log.i("debug", "count: " + room.fbUsers.count())
//                    ChatBroadcast.send(ChatBroadcast.addedRoom)
                } else {
                    callback?.onError(-1, "No data for room")
                }
            }

            override fun onCancelled(p0: DatabaseError?) {
                callback?.onError(-1, p0.toString())
            }
        })
    }


    fun getUser(id: String, callback: Callback) {
        val listener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot?) {
                if (!p0!!.exists()) {
                    callback.onSuccess(null)
                } else {
                    callback.onSuccess(FBUser(p0))
                }
            }

            override fun onCancelled(p0: DatabaseError?) {
                callback.onError(-1, p0.toString())
            }
        }

        this.getUserRef().child(id).addListenerForSingleValueEvent(listener)
    }

    fun setUser(user: FBUser, callback: Callback?) {
        this.getUserRef().child(user.id).setValue(user.value()) { error, _ ->
            if (error == null) {
                callback?.onSuccess(user)
            } else {
                callback?.onError(-1, "Couldn't insert a user!!")
            }
        }

    }

    fun updateUser(user: FBUser, callback: Callback?) {
        this.getUserRef().child(user.id).updateChildren(user.value()) { error, _ ->
            if (error == null) {
                callback?.onSuccess(user)
            } else {
                callback?.onError(-1, "Couldn't insert a user!!")
            }
        }

    }

    fun addRoom(room: MessageRoom, user: FBUser, callback: Callback?) {
        val roomReference = this.getUserRef().child(user.id)
                .child("rooms").child(room.id)

        val hashMap = HashMap<String, Any>()
        hashMap["type"] = if (room.type == RoomType.private) "private" else "public"

        roomReference.setValue(hashMap) { err, _ ->
            if (err != null) {
                callback?.onSuccess(room)
            } else {
                callback?.onError(-1, "Couldn't create room chat")
            }
        }
    }

    fun sendMessage(message: Message, room: MessageRoom, callback: Callback) {
        this.getRoomMessageRef().child(room.id).child(message.id).setValue(message.value()) { error, _ ->
            if (error == null) {
                callback.onSuccess(message)
            } else {
                callback.onError(-1, "Couldn't send the message")
            }
        }
    }
}