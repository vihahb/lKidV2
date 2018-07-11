package com.snq.ikidz.teacher.sdk.callback

interface ItemMoreActionGeneric<T> {
    fun ItemClick(type: Int, id: Int, data: T)
}