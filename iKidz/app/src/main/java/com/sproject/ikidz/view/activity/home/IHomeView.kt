package com.sproject.ikidz.view.activity.home

import com.sproject.ikidz.model.RESP.RESP_CurrentClassTeacher
import com.sproject.ikidz.model.entity.CountNotify
import com.sproject.ikidz.model.entity.DataUser
import com.sproject.ikidz.model.entity.ItemDrawer
import com.sproject.ikidz.view.base.IBasicActivity
import java.util.ArrayList

interface IHomeView : IBasicActivity{
    fun setDataUser(dataUser: DataUser)
    fun getCurrentClassSuccess(data: RESP_CurrentClassTeacher)
    fun getCurrentClassError(s: String)
    fun onSaveClassSuccess()
    fun onSaveClassError()
    fun initDrawerSuccess(data: ArrayList<ItemDrawer>)
    fun getSetNotify(data: CountNotify)
}