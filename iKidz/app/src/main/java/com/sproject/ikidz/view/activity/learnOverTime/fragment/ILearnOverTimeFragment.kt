package com.sproject.ikidz.view.activity.learnOverTime.fragment

import com.sproject.ikidz.model.entity.LearnOverTimeEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface ILearnOverTimeFragment : IBasicActivity {
    fun getMoreTimeSuccess(data: List<LearnOverTimeEntity>)
    fun getMoreTimeError(errorMessage: String)
}