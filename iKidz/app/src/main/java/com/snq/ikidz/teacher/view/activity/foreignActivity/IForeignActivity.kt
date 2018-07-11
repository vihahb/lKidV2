package com.snq.ikidz.teacher.view.activity.foreignActivity

import com.snq.ikidz.teacher.model.entity.ForeignActivityEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface IForeignActivity : IBasicActivity {

    fun getForeignSuccess(list: List<ForeignActivityEntity>)
    fun getForeignError(message: String)

}