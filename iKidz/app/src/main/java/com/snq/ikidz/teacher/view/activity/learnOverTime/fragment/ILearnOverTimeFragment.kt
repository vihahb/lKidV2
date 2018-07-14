package com.snq.ikidz.teacher.view.activity.learnOverTime.fragment

import com.snq.ikidz.teacher.model.entity.LearnOverTimeEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ILearnOverTimeFragment : IBasicActivity {
    fun getMoreTimeSuccess(data: List<LearnOverTimeEntity>)
    fun getMoreTimeError(errorMessage: String)
    fun validOTError()
    fun validOTSuccess(position: Int)
    fun updateTimePickSuccess(position: Int, timePickReturn: String)
    fun updateTimePickError()
}