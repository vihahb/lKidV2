package com.snq.teacher.view.activity.mbr

import com.snq.teacher.model.entity.StudentsEntity
import com.snq.teacher.view.base.IBasicActivity

interface IMbr : IBasicActivity {
    fun getMrbSuccess(list: List<StudentsEntity>)
    fun getMbrError(message: String)
}