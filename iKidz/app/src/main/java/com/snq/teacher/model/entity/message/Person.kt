package com.snq.teacher.model.entity.message

import com.snq.teacher.sdk.Utils.safe
import com.snq.teacher.sdk.extension.toDate
import org.json.JSONObject
import java.io.Serializable
import java.util.*

open class Person : Serializable {
    var id: Int = 0
    var fullName = ""
    var birthday: Date? = null
    var userId: Int = 0
    var username: String = ""
    var gender = 0
    var phone = ""
    var email = ""
    var address = ""
    var avatar = ""
    var job: String = ""
    var indentifyNumber: String = ""

    constructor(data: JSONObject?) {
        this.id = data?.safe<Int>("id") ?: 0
        this.fullName = data?.safe<String>("full_name") ?: ""
        //todo: Chờ bên api thống nhất kiểu dữ liệu trả về boolean, hay integer
//        this.gender = data?.safe<Int>("gender") ?: 0
        this.gender = 0
        this.phone = data?.safe<String>("phone") ?: ""
        this.email = data?.safe<String>("email") ?: ""
        this.avatar = data?.safe<String>("avatar") ?: ""
        val dateString = data?.safe<String>("birthday") ?: ""
        birthday = dateString.toDate("dd-MM-yyyy")

        this.username = data?.safe<String>("username") ?: ""
        this.userId = data?.safe<Int>("user_id") ?: 0
        job = data?.safe<String>("job") ?: ""
        address = data?.safe<String>("address") ?: ""
        val a = data?.safe<String>("indentify") ?: ""
        indentifyNumber = data?.safe<String>("indentify") ?: ""
    }

    constructor() {}
}