package com.snq.ikidz.teacher.model.entity.message

import com.snq.ikidz.teacher.sdk.Utils.safe
import org.json.JSONObject

class Teacher : Person {
    var firebaseId: String = ""
    var teacherCode: String = ""

    constructor(teacher: JSONObject?) : super(teacher) {
        teacherCode = teacher?.safe<String>("teacher_code") ?: ""
        firebaseId = teacher?.safe<String>("firebase_id") ?: ""
    }
}