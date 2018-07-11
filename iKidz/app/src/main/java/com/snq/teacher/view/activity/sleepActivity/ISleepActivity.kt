package com.snq.teacher.view.activity.sleepActivity

import com.snq.teacher.model.entity.SleepEntity
import com.snq.teacher.model.entity.ValueSleepEntity
import com.snq.teacher.view.base.IBasicActivity

interface ISleepActivity : IBasicActivity {
    fun getSleepData(data: List<SleepEntity>)
    fun getSleepDataError(errorMessage: String)
    fun updateTimeSleepSuccess()
    fun updateTimeSleepError()
    fun updateChildSuccess(pos: Int, childValue: ValueSleepEntity)
    fun updateChildError()
}