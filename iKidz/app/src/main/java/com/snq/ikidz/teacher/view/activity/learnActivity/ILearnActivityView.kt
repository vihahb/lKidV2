package com.snq.ikidz.teacher.view.activity.learnActivity

import com.snq.ikidz.teacher.model.entity.LearnActivityEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ILearnActivityView : IBasicActivity {
    fun getLearnActivitySuccess(data: List<LearnActivityEntity>)
    fun getLearnActivityError(errorMessage: String)
    fun createOrUpdateSuccess(learningMorning: String, learningAfternoon: String, date: String, pos: Int)
    fun createOrUpdateError()

}