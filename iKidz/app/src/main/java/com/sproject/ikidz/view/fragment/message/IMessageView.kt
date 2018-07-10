package com.sproject.ikidz.view.fragment.message

import com.sproject.ikidz.model.entity.DataUser
import com.sproject.ikidz.view.base.IBasicActivity

interface IMessageView:IBasicActivity {
    fun getUsserSuccess(user:DataUser)
}