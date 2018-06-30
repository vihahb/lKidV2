package com.sproject.ikidz.view.activity.learnProgram

import com.sproject.ikidz.model.entity.DataWeek
import com.sproject.ikidz.view.base.IBasicActivity

interface ILearnProgram: IBasicActivity {
    fun getLearnProgramSuccess(data:List<DataWeek>)
    fun getLearnProgramerror(errorMessage: String)
}