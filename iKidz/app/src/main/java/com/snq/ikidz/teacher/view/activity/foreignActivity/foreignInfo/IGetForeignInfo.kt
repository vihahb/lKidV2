package com.snq.ikidz.teacher.view.activity.foreignActivity.foreignInfo

import com.snq.ikidz.teacher.model.entity.DataForeignInfo
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface IGetForeignInfo : IBasicActivity {
    fun getForeignInfoSuccess(info: DataForeignInfo)
    fun getForeignError(s: String)
}