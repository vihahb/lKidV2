package com.snq.teacher.view.fragment.message

import com.snq.teacher.model.entity.DataUser
import com.snq.teacher.view.base.IBasicActivity

interface IMessageView:IBasicActivity {
    fun getUsserSuccess(user:DataUser)
}