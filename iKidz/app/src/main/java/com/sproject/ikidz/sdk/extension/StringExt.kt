package com.sproject.ikidz.sdk.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(format: String = "dd/MM/yyyy"): Date? {
    val dateFormat = SimpleDateFormat(format)
    try {
        val date = dateFormat.parse(this)
        return date
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return null
}

fun String.getContent(): String {
    var content = this
    content = content.replace("<p>", "").replace("</p>", "")
    content = content.replace("<br>", "\n")
    content = content.trim()
    return content
}