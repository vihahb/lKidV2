package com.snq.teacher.view.activity.schoolProfile.info

import com.snq.teacher.model.entity.ProfileInfoEntify
import com.snq.teacher.view.base.IBasicActivity

interface IProfileView : IBasicActivity {
    fun getProfileSuccess(data: ProfileInfoEntify?)
    fun getProfileError()
    fun getSampleReviewError()
    fun getSampleReviewSuccess(data: List<String>)
    fun updateProfileSuccess()
    fun updateProfileError()

}