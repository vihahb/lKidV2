package com.snq.teacher.view.fragment.phonebook

import com.snq.teacher.model.entity.ClassPeopleEntity
import com.snq.teacher.view.base.IBasicActivity
import java.util.*

interface IPhoneBooks : IBasicActivity {
    fun getPhoneBookSuccess(dataList: ArrayList<ClassPeopleEntity>)
    fun getPhoneBookError(errorMessage: String)
}