package com.sproject.ikidz.sdk.extension

/**
 * Created by kem on 9/10/17.
 */

import java.text.SimpleDateFormat
import java.util.*


fun Date.string(format: String = "dd/MM/yyyy"): String {
    val formatter = SimpleDateFormat(format)
    return formatter.format(this)
}

fun Date.timePeriod(): String {
    val currentTime = System.currentTimeMillis()
    val delta = (currentTime - this.time) / 1000

    if (delta < 60) {
        return "just now"
    }

    val minutes = delta / 60

    if (minutes < 60) {
        return if (minutes > 1) ("" + minutes + " minutes") else "a minute"
    }

    val hours = minutes / 60

    if (hours < 24) {
        return if (hours > 1) ("" + hours + " hours") else "an hour"
    }

    val days = hours / 24

    if (days == 1L) {
        return "Yesterday at " + this.string("h:mma")
    }

    return this.string()

}