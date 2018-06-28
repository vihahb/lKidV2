package com.sproject.ikidz.view.activity.sleepActivity

import com.sproject.ikidz.model.entity.SleepEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface ISleepActivity : IBasicActivity {
    fun getSleepData(data: List<SleepEntity>)
    fun getSleepDataError(errorMessage: String)
}