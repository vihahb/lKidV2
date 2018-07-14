package com.snq.ikidz.teacher.view.activity.baseMessage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.database.GetObjectByKeyModel
import com.snq.ikidz.teacher.model.entity.DataUser
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.message.ChatMessageEntity
import com.snq.ikidz.teacher.model.message.MetadataEntity
import com.snq.ikidz.teacher.model.message.RoomChatMessage
import com.snq.ikidz.teacher.model.message.utils.FirebaseDatabaseUtils
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils
import java.util.*


class BaseMessageActivity : AppCompatActivity() {

    var TAG = "BaseMessageActivity"

    private lateinit var mDatabaseRoom: DatabaseReference
    private lateinit var mDatabaseMetaData: DatabaseReference
    private lateinit var mDatabaseRef: DatabaseReference

    //    lateinit var messageList: ArrayList<ChatMessageEntity>
//    lateinit var dataChatMessage: RoomChatMessage

    var list_childs: ArrayList<ChatMessageEntity>? = null

//    lateinit var listMetaData: ArrayList<MetadataEntity>

    var list: ArrayList<RoomChatMessage>? = null

    var firebase: FirebaseApp? = null
    lateinit var firebaseAuth: FirebaseAuth

    lateinit var data: DataUser

    var tokenFirebase = ""
    var token = ""
    var schoolCode = SharedUtils.getInstance().getStringValue(Constants.SCHOOL_CODE)
    var userId = ""

    lateinit var listMetaData :ArrayList<MetadataEntity>
    lateinit var listRoom :List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_message)
        listMetaData = ArrayList<MetadataEntity>()
        getUser()

//        token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
//        tokenFirebase = SharedUtils.getInstance().getStringValue(Constants.FIREBASE_TOKEN)
//
//        firebase = FirebaseApp.initializeApp(this)
//        firebaseAuth = FirebaseAuth.getInstance(firebase!!)
//        firebaseAuth.signInWithCustomToken(tokenFirebase).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                // Sign in success, update UI with the signed-in user's information
//                Log.e(TAG, "signInWithCustomToken:success")
//                val user = firebaseAuth.currentUser
//                Log.e(TAG, "User: " + user.toString())
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.e(TAG, "signInWithCustomToken:failure", task.exception)
//                Toast.makeText(this@BaseMessageActivity, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//            }
//        }
////        initChatAuth()
//        mDatabaseRef = FirebaseDatabase.getInstance().reference
//        mDatabaseRef.database.getReference("users/$userId/rooms")
//        mDatabaseRef.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(databaseError: DatabaseError?) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError!!.toException())
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot?) {
//
//                for (child in dataSnapshot!!.children) {
//                    Log.e("Room metadata: ", " - " + child.toString())
//                }
//
//                dataSnapshot!!.children.toString()
//                Log.e("Count mDatabaseRef", "" + dataSnapshot!!.childrenCount)
//            }
//
//        })
//
//        retrieveJoinedRoom()
//        retrieveFirebaseMessage()
    }
//
//    private fun retrieveJoinedRoom() {
//        listMetaData = ArrayList()
//        mDatabaseMetaData = FirebaseDatabase.getInstance().getReference("room-metadata/" + userId)
//        mDatabaseMetaData.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(databaseError: DatabaseError?) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError!!.toException())
//            }
//
//            @SuppressLint("LongLogTag")
//            override fun onDataChange(dataSnapshot: DataSnapshot?) {
//                Log.e("Count retrieveJoinedRoom", "" + dataSnapshot!!.childrenCount)
//
//                for (child in dataSnapshot.children) {
//                    val meta = child.getValue(MetadataEntity::class.java)
//                    listMetaData.add(meta!!)
//                    Log.e("Room metadata: ", " - " + listMetaData.toString())
//                }
//            }
//
//        })
//    }
//
//
//    fun retrieveFirebaseMessage() {
//        list = ArrayList()
//        mDatabaseRoom = FirebaseDatabase.getInstance().getReference("room-messages")
//        mDatabaseRoom.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(databaseError: DatabaseError?) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError!!.toException())
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot?) {
//
////                dataChatMessage = dataSnapshot!!.getValue(RoomChatMessage::class.java)!!
//
//                Log.e("Count ", "" + dataSnapshot!!.childrenCount)
//
//                for (child in dataSnapshot.children) {
//                    var roomChatMessage = RoomChatMessage(child.children)
//                    list!!.add(roomChatMessage)
//                    Log.e("Room: ", " - " + roomChatMessage.toString())
////
////                    for (chat_childs in child.children){
////                        var childss = chat_childs.getValue(ChatMessageEntity::class.java)!!
////                        list_childs!!.add(childss)
////                        Log.d("Childs: ", " - " + childss.toString())
////                    }
//                }
//
////                var listRoom = ArrayList<RoomChatMessage>()
////                listRoom = dataSnapshot.getValue(genericTypeIndicator)!!
////
////                for (i in listRoom){
////                    Log.e("Data " ,""+ i.toString())
////                }
//            }
//        })
//
//    }

    fun getUser() {
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        if (token != null) {
            object : GetObjectByKeyModel<DataUser>(DataUser::class.java, "token", token) {
                override fun onError(message: ErrorEntity?) {
                    iKidApplications.log(TAG, "getUser error: " + message!!.errorMessage)
                }

                override fun onSuccess(`object`: DataUser?) {
                    if (`object` != null) {
                        data = `object`
                        userId = schoolCode + "_" + `object`.user.id
                        listRoom = FirebaseDatabaseUtils.getRoomJoined(userId)

                        if (listRoom.isNotEmpty()){
                            for (s in listRoom){
                                var metadata = FirebaseDatabaseUtils.getRoomMetadata(s)
                                listMetaData.add(metadata)
                                Log.e(TAG, "Metadata foreach: " + metadata.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}
