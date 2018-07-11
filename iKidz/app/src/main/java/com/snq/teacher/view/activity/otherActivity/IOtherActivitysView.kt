package com.snq.teacher.view.activity.otherActivity

import com.snq.teacher.model.entity.OtherActivitysEntity
import com.snq.teacher.model.entity.ValueSleepEntity
import com.snq.teacher.view.base.IBasicActivity

interface IOtherActivitysView : IBasicActivity {
    fun getOthersList(data: List<OtherActivitysEntity>)
    fun getOthersListError(error: String)
    fun updateOtherValueError()
    fun updateOtherValueSuccess(pos: Int, otherValue: ValueSleepEntity)
}