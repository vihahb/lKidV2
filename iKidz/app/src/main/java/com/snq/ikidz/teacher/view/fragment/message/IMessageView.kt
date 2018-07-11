package com.snq.ikidz.teacher.view.fragment.message

import com.snq.ikidz.teacher.model.entity.DataUser
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface IMessageView:IBasicActivity {
    fun getUsserSuccess(user:DataUser)
}