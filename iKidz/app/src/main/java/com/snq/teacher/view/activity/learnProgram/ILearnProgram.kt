package com.snq.teacher.view.activity.learnProgram

import com.snq.teacher.model.entity.DataWeek
import com.snq.teacher.view.base.IBasicActivity

interface ILearnProgram : IBasicActivity {
    fun getLearnProgramSuccess(data: List<DataWeek>)
    fun getLearnProgramerror(errorMessage: String)
}