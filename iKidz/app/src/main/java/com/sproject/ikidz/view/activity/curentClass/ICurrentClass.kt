package com.sproject.ikidz.view.activity.curentClass

import com.sproject.ikidz.model.entity.StudentEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface ICurrentClass : IBasicActivity {

    fun getCurrentClassSuccess(data: List<StudentEntity>)
    fun getClassError(errorMessage: String?)

}