package com.snq.ikidz.teacher.view.fragment.phonebook

import com.snq.ikidz.teacher.model.entity.ClassPeopleEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity
import java.util.*

interface IPhoneBooks : IBasicActivity {
    fun getPhoneBookSuccess(dataList: ArrayList<ClassPeopleEntity>)
    fun getPhoneBookError(errorMessage: String)
}