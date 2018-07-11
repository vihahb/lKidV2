package com.snq.teacher.view.activity.foreignActivity.foreignInfo

import com.snq.teacher.model.entity.DataForeignInfo
import com.snq.teacher.view.base.IBasicActivity

interface IGetForeignInfo : IBasicActivity {
    fun getForeignInfoSuccess(info: DataForeignInfo)
    fun getForeignError(s: String)
}