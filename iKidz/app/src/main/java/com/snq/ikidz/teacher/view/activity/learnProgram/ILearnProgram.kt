package com.snq.ikidz.teacher.view.activity.learnProgram

import com.snq.ikidz.teacher.model.entity.DataWeek
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ILearnProgram : IBasicActivity {
    fun getLearnProgramSuccess(data: List<DataWeek>)
    fun getLearnProgramerror(errorMessage: String)
}