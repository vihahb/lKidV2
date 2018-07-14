package com.snq.teacher.model.entity.message

import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.SharedUtils
import com.snq.teacher.sdk.Utils.safe
import com.snq.teacher.sdk.callback.Callback
import org.json.JSONObject

class User {
    var userId = 0
    var username = ""
    var password = ""
    var status = ""
    var type = ""
    var avatar = ""
    var email = ""
    var role: Role? = null
    var person: Person? = null

    var fbUser: FBUser? = null
        set (value) {
            field = value
            if (this == User.current && value != null) {
                value.fetchRoom()
            }
        }

    constructor(userData: JSONObject?) {
        if (userData == null) {
            return
        }
        this.userId = userData.safe<Int>("id", 0)
        this.username = userData.safe<String>("username", "")
        this.type = userData.safe<String>("user_type", "")
        this.avatar = userData.safe<String>("avatar", "")
        this.email = userData.safe<String>("email", "")
        this.status = userData.safe<String>("status", "")

        if (type == "parent") {
            this.person = Parent(userData.safe<JSONObject>("parents"))

        } else {
            this.person = Teacher(userData.safe<JSONObject>("teachers"))
        }
        this.role = Role(userData.safe<JSONObject>("roles"))
    }

    fun getFBUser() {
        val schoolCode = SharedUtils.getInstance().getStringValue(Constants.SCHOOL_CODE)
//        val schoolCode = Sha[SettingKey.schoolCode]

        val id = schoolCode + "_" + current!!.userId
        FirebaseService.getUser(id, object : Callback {
            override fun onError(code: Int, message: String) {
                current!!.fbUser = null
            }

            override fun onSuccess(data: Any?) {
                if (data == null) {
                    setFBUser()
                } else {
                    current?.fbUser = data as FBUser
                }
            }
        })
    }

    fun setFBUser() {
        val id = SharedUtils.getInstance().getStringValue(Constants.SCHOOL_CODE) + "_" + this.userId
        val newFBUser = FBUser(id, this.person!!.fullName, this.avatar)
        FirebaseService.setUser(newFBUser, object : Callback {
            override fun onError(code: Int, message: String) {
                current?.fbUser = null
            }

            override fun onSuccess(data: Any?) {
                current?.fbUser = data as FBUser
            }
        })
    }

    companion object {
        var current: User? = null
            set(value) {
                field = value
                current?.getFBUser()
            }
    }
}