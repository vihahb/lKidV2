package com.snq.teacher.view.activity.curentClass

import com.snq.teacher.model.entity.StudentEntity
import com.snq.teacher.view.base.IBasicActivity

interface ICurrentClass : IBasicActivity {

    fun getCurrentClassSuccess(data: List<StudentEntity>)
    fun getClassError(errorMessage: String?)

}