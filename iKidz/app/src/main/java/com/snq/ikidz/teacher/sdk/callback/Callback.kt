package com.snq.ikidz.teacher.sdk.callback

interface Callback {

    fun onSuccess(data: Any?)

    fun onError(code: Int, message: String)
}