package com.snq.ikidz.teacher.view.activity.sleepActivity

import com.snq.ikidz.teacher.model.entity.SleepEntity
import com.snq.ikidz.teacher.model.entity.ValueSleepEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ISleepActivity : IBasicActivity {
    fun getSleepData(data: List<SleepEntity>)
    fun getSleepDataError(errorMessage: String)
    fun updateTimeSleepSuccess()
    fun updateTimeSleepError()
    fun updateChildSuccess(pos: Int, childValue: ValueSleepEntity)
    fun updateChildError()
}