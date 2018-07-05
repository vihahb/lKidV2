package com.sproject.ikidz.view.activity.otherActivity

import com.sproject.ikidz.model.entity.OtherActivitysEntity
import com.sproject.ikidz.model.entity.ValueSleepEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface IOtherActivitysView : IBasicActivity {
    fun getOthersList(data: List<OtherActivitysEntity>)
    fun getOthersListError(error: String)
    fun updateOtherValueError()
    fun updateOtherValueSuccess(pos: Int, otherValue: ValueSleepEntity)
}