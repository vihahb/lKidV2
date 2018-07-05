package com.sproject.ikidz.view.activity.schoolProfile.info

import com.sproject.ikidz.model.entity.ProfileInfoEntify
import com.sproject.ikidz.view.base.IBasicActivity

interface IProfileView : IBasicActivity {
    fun getProfileSuccess(data: ProfileInfoEntify?)
    fun getProfileError()
    fun getSampleReviewError()
    fun getSampleReviewSuccess(data: List<String>)
    fun updateProfileSuccess()
    fun updateProfileError()

}