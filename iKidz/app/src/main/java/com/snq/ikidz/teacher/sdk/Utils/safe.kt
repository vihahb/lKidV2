package com.snq.ikidz.teacher.sdk.Utils

import org.json.JSONArray
import org.json.JSONObject

fun <T> JSONObject.safe(name: String): T? {
    if (this.has(name) && !this.isNull(name)) {
        return this.opt(name) as? T
    } else {
        return null
    }
}

fun <T> JSONObject.safe(name: String, default: T): T {
    if (this.has(name) && !this.isNull(name)) {
        return (this.opt(name) as? T) ?: default
    } else {
        return default
    }
}

fun <T> JSONArray.safe(index: Int): T? {
    if (index < this.length()) {
        return this.opt(index) as? T
    }
    return null
}