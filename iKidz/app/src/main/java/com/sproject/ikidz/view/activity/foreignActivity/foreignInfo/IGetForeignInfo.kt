package com.sproject.ikidz.view.activity.foreignActivity.foreignInfo

import com.sproject.ikidz.model.entity.DataForeignInfo
import com.sproject.ikidz.view.base.IBasicActivity

interface IGetForeignInfo : IBasicActivity {
    fun getForeignInfoSuccess(info: DataForeignInfo)
    fun getForeignError(s: String)
}