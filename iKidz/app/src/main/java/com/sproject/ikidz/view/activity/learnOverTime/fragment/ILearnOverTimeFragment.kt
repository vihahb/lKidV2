package com.sproject.ikidz.view.activity.learnOverTime.fragment

import com.sproject.ikidz.model.entity.LearnOverTimeEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface ILearnOverTimeFragment : IBasicActivity {
    fun getMoreTimeSuccess(data: List<LearnOverTimeEntity>)
    fun getMoreTimeError(errorMessage: String)
    fun validOTError()
    fun validOTSuccess(position: Int)
    fun updateTimePickSuccess(position: Int, timePickReturn: String)
    fun updateTimePickError()
}