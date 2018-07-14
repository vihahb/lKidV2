package com.snq.ikidz.teacher.view.activity.mbr

import com.snq.ikidz.teacher.model.entity.StudentsEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface IMbr : IBasicActivity {
    fun getMrbSuccess(list: List<StudentsEntity>)
    fun getMbrError(message: String)
}