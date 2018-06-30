package com.sproject.ikidz.view.activity.learnActivity

import com.sproject.ikidz.model.entity.LearnActivityEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface ILearnActivityView : IBasicActivity {
    fun getLearnActivitySuccess(data: List<LearnActivityEntity>)
    fun getLearnActivityError(errorMessage: String)

}