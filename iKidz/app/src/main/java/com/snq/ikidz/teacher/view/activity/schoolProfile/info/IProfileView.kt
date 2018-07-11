package com.snq.ikidz.teacher.view.activity.schoolProfile.info

import com.snq.ikidz.teacher.model.entity.ProfileInfoEntify
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface IProfileView : IBasicActivity {
    fun getProfileSuccess(data: ProfileInfoEntify?)
    fun getProfileError()
    fun getSampleReviewError()
    fun getSampleReviewSuccess(data: List<String>)
    fun updateProfileSuccess()
    fun updateProfileError()

}