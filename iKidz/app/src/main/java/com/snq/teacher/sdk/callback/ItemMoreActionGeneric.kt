package com.snq.teacher.sdk.callback

interface ItemMoreActionGeneric<T> {
    fun ItemClick(type: Int, id: Int, data: T)
}