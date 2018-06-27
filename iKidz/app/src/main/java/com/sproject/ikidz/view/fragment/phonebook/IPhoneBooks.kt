package com.sproject.ikidz.view.fragment.phonebook

import com.sproject.ikidz.model.entity.ClassPeopleEntity
import com.sproject.ikidz.view.base.IBasicActivity
import java.util.ArrayList

interface IPhoneBooks : IBasicActivity {
    fun getPhoneBookSuccess(dataList: ArrayList<ClassPeopleEntity>)
    fun getPhoneBookError(errorMessage: String)
}