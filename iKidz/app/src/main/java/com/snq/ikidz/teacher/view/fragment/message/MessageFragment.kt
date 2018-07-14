package com.snq.ikidz.teacher.view.fragment.message

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.entity.DataUser
import com.snq.ikidz.teacher.model.message.ChatMessageEntity
import com.snq.ikidz.teacher.model.message.MessageView
import com.snq.ikidz.teacher.model.message.MetadataEntity
import com.snq.ikidz.teacher.model.message.UserData
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.JsonHelper
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils
import com.snq.ikidz.teacher.sdk.callback.ItemClickListenerGeneric
import com.snq.ikidz.teacher.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_message.*
import java.util.*

class MessageFragment : BaseFragment(), IMessageView {
    override fun getUsserSuccess(user: DataUser) {
        data = user
        userId = schoolCode + "_" + `data`.user.id
//        getRoomKeyJoined(userId)
    }

    var TAG = "MessageFragment"


    lateinit var data: DataUser

    lateinit var presenter: MessageFragmentPresenter

    var schoolCode = SharedUtils.getInstance().getStringValue(Constants.SCHOOL_CODE)
    var userId = ""
//    lateinit var listRoom: ArrayList<String>
//    lateinit var listRoomMeta: ArrayList<MetadataEntity>
//    lateinit var listUser: ArrayList<UserData>
//    lateinit var listLastMessage: ArrayList<ChatMessageEntity>

    lateinit var referencesDB: DatabaseReference
    lateinit var metadataRoom: DatabaseReference
    lateinit var userDb: DatabaseReference
    lateinit var messageDb: DatabaseReference

    lateinit var adapterMessageList: AdapterMessageList
    lateinit var listMessage: ArrayList<MessageView>

    companion object {

        fun newInstance(): MessageFragment {
            val args = Bundle()
            val fragment = MessageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MessageFragmentPresenter(this)
//        listRoom = ArrayList()
        listMessage = ArrayList()
        adapterMessageList = AdapterMessageList(listMessage, context, object : ItemClickListenerGeneric<MessageView> {
            override fun ItemClick(id: Int, data: MessageView?) {

            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        btnGotoMessage.setOnClickListener {
//            startActivity(Intent(context, BaseMessageActivity::class.java))
//        }
        initView()
        initRealTimeDB()
        presenter.getUser()
    }

    private fun initRealTimeDB() {

        referencesDB = FirebaseDatabase.getInstance().reference
        metadataRoom = FirebaseDatabase.getInstance().reference
        userDb = FirebaseDatabase.getInstance().reference
        messageDb = FirebaseDatabase.getInstance().reference
    }

    private fun initView() {
        rcl_message.layoutManager = LinearLayoutManager(context)
        rcl_message.adapter = adapterMessageList
    }

    private fun getRoomKeyJoined(user_id: String) {

        /**
         * Get các room đã join
         **/
        referencesDB.database.getReference("users/$user_id/rooms").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p1: DataSnapshot) {
                val messageView = MessageView()

                var metadata = MetadataEntity()
                var userData = UserData()
                var lastMessage = ChatMessageEntity()

                for (child in p1.children) {
//                    listRoom.add(child.key)
                    var roomKey = child.key
                    Log.e(TAG, "getRoomJoined: " + child.key)

                    if (!roomKey.isNullOrEmpty()) {

                        /**
                         * Get các chi tiết data của room
                         **/
                        referencesDB.database.getReference("room-metadata/$roomKey").addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(p2: DataSnapshot?) {
                                metadata = p2!!.getValue(MetadataEntity::class.java)!!
                                Log.e(TAG, "getRomMetadata: " + metadata.toString())

                                if (metadata != null) {
                                    var receiver = ""
                                    if (userId == metadata.receiver)
                                        receiver = metadata.createdBy
                                    else
                                        receiver = metadata.receiver


                                    /**
                                     * Get các chi tiết user để hiển thị
                                     **/
                                    referencesDB.database.getReference("users/$receiver").addValueEventListener(object : ValueEventListener {
                                        override fun onCancelled(databaseError: DatabaseError) {
                                            // Getting Post failed, log a message
                                            Log.e(TAG, "loadPost:onCancelled", databaseError.toException())
                                        }

                                        override fun onDataChange(p3: DataSnapshot) {
                                            userData = p3.getValue(UserData::class.java)!!
                                            Log.e(TAG, "getUser: " + userData.toString())

                                            if (userData != null) {

                                                /**
                                                 * Get tin nhắn cuối cùng của room
                                                 **/
                                                referencesDB.database.getReference("room-messages/$roomKey").limitToLast(1).addValueEventListener(object : ValueEventListener {
                                                    override fun onCancelled(databaseError: DatabaseError) {
                                                        // Getting Post failed, log a message
                                                        Log.e(TAG, "loadPost:onCancelled", databaseError.toException())
                                                    }

                                                    override fun onDataChange(p4: DataSnapshot) {
                                                        p4.children.forEachIndexed { index, dataSnapshot ->
                                                            lastMessage = dataSnapshot.getValue(ChatMessageEntity::class.java)!!
                                                            Log.e(TAG, "forEachIndexed---LastMessage: " + lastMessage.toString())
                                                        }

                                                        Log.e(TAG, "getLastMessage: " + lastMessage.toString())

                                                        messageView.lastMessage = lastMessage
                                                        messageView.user = userData
                                                        messageView.metadata = metadata

                                                        listMessage.add(messageView)

                                                        Log.e(TAG, "listRoom.forEachIndexed: " + messageView.toString())

                                                        if (listMessage.isNotEmpty()) {
                                                            adapterMessageList.notifyDataSetChanged()
                                                        }
                                                    }
                                                })
                                            }
                                        }
                                    })

                                }

                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                Log.e(TAG, "loadPost:onCancelled", databaseError.toException())
                            }
                        })
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.e(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        })

        Log.e(TAG, "Message List: " + JsonHelper.toJson(listMessage))
    }

//    private fun getRoomMetadata(roomKey: String): MetadataEntity? {
//        var dataRoom = MetadataEntity()
//        metadataRoom.database.getReference("room-metadata/$roomKey").ref.addChildEventListener(object : ChildEventListener {
//            override fun onCancelled(databaseError: DatabaseError) {
//                Log.e(TAG, "loadPost:onCancelled", databaseError.toException())
//            }
//
//            override fun onChildMoved(dataSnapshot: DataSnapshot?, p1: String?) {
//                dataRoom = dataSnapshot!!.getValue(MetadataEntity::class.java)!!
//                Log.e(TAG, "onChildMoved: " + dataRoom.toString())
//            }
//
//            override fun onChildChanged(dataSnapshot: DataSnapshot?, p1: String?) {
//                dataRoom = dataSnapshot!!.getValue(MetadataEntity::class.java)!!
//                Log.e(TAG, "onChildChanged: " + dataRoom.toString())
//            }
//
//            override fun onChildAdded(dataSnapshot: DataSnapshot?, p1: String?) {
//                dataRoom = dataSnapshot!!.getValue(MetadataEntity::class.java)!!
//                Log.e(TAG, "onChildAdded: " + dataRoom.toString())
//            }
//
//            override fun onChildRemoved(p0: DataSnapshot?) {
//                Log.e(TAG, "onChildRemoved" + p0!!.children.toString())
//            }
//        })
//        return dataRoom
//    }

//    private fun getUserByReceiver(receiver: String): UserData {
//        var userData = UserData()
//        userDb.database.getReference("users/$receiver").ref.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.e(TAG, "loadPost:onCancelled", databaseError.toException())
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                userData = dataSnapshot.getValue(UserData::class.java)!!
//                Log.e(TAG, "getRoomMetadata: " + userData.toString())
//            }
//        })
//        return userData
//    }
//
//    private fun getLastMessage(roomKey: String): ChatMessageEntity {
//        var lastMessage = ChatMessageEntity()
//        messageDb.database.getReference("room-messages/$roomKey").ref.limitToLast(1).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.e(TAG, "loadPost:onCancelled", databaseError.toException())
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                lastMessage = dataSnapshot.getValue(ChatMessageEntity::class.java)!!
//                Log.e(TAG, "getRoomMetadata: " + lastMessage.toString())
//            }
//        })
//        return lastMessage
//    }

    /*if (listRoom.isNotEmpty()){
        for (s in listRoom){
            var metadata = FirebaseDatabaseUtils.getRoomMetadata(s)
            listMetaData.add(metadata)
            Log.e(TAG, "Metadata foreach: " + metadata.toString())
        }
    }*/
}