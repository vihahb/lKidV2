package com.sproject.ikidz.view.activity.mbr

import com.sproject.ikidz.model.entity.StudentsEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface IMbr : IBasicActivity {
    fun getMrbSuccess(list: List<StudentsEntity>)
    fun getMbrError(message: String)
}