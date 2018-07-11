package com.snq.teacher.view.activity.foreignActivity

import com.snq.teacher.model.entity.ForeignActivityEntity
import com.snq.teacher.view.base.IBasicActivity

interface IForeignActivity : IBasicActivity {

    fun getForeignSuccess(list: List<ForeignActivityEntity>)
    fun getForeignError(message: String)

}