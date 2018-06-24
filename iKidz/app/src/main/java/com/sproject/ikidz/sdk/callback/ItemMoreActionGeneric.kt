package com.sproject.ikidz.sdk.callback

interface ItemMoreActionGeneric<T> {
    fun ItemClick(type: Int, id: Int, data: T)
}