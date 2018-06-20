package com.sproject.ikidz.view.activity.foreignActivity

import com.sproject.ikidz.model.entity.ForeignActivityEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface IForeignActivity : IBasicActivity {

    fun getForeignSuccess(list: List<ForeignActivityEntity>)
    fun getForeignError(message: String)

}