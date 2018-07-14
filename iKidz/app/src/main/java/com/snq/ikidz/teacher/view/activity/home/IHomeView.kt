package com.snq.ikidz.teacher.view.activity.home

import com.snq.ikidz.teacher.model.RESP.RESP_CurrentClassTeacher
import com.snq.ikidz.teacher.model.entity.CountNotify
import com.snq.ikidz.teacher.model.entity.DataUser
import com.snq.ikidz.teacher.model.entity.ItemDrawer
import com.snq.ikidz.teacher.view.base.IBasicActivity
import java.util.*

interface IHomeView : IBasicActivity {
    fun setDataUser(dataUser: DataUser)
    fun getCurrentClassSuccess(data: RESP_CurrentClassTeacher)
    fun getCurrentClassError(s: String)
    fun onSaveClassSuccess()
    fun onSaveClassError(errorMessage: String)
    fun initDrawerSuccess(data: ArrayList<ItemDrawer>)
    fun getSetNotify(data: CountNotify)
}