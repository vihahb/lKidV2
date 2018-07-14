package com.snq.teacher.model.entity.message

import com.snq.teacher.sdk.Utils.safe
import org.json.JSONObject
import java.io.Serializable

class Parent : Person, Serializable {
    var relationship_name = ""

    constructor(parentData: JSONObject?) : super(parentData) {
        relationship_name = parentData?.safe<String>("relationship_name") ?: ""
    }
}
