package com.snq.ikidz.teacher.view.activity.curentClass

import com.snq.ikidz.teacher.model.entity.StudentEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ICurrentClass : IBasicActivity {

    fun getCurrentClassSuccess(data: List<StudentEntity>)
    fun getClassError(errorMessage: String?)

}