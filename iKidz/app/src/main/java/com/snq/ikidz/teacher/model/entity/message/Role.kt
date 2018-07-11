package com.snq.ikidz.teacher.model.entity.message

import com.snq.ikidz.teacher.sdk.Utils.safe
import org.json.JSONObject

class Role {
    internal var roleId = 0
    internal var name = ""
    internal var slug = ""
    internal var description = ""

    constructor() {}

    constructor(roleData: JSONObject?) {
        if (roleData == null) {
            return
        }
        this.roleId = roleData.safe<Int>("id", 0)
        this.name = roleData.safe<String>("name", "")
        this.slug = roleData.safe<String>("slug", "")
        this.description = roleData.safe<String>("description", "")
    }
}