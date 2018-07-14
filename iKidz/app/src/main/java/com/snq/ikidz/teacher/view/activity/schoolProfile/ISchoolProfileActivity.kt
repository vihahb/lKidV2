package com.snq.ikidz.teacher.view.activity.schoolProfile

import com.snq.ikidz.teacher.model.entity.ProfileEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ISchoolProfileActivity : IBasicActivity {
    fun getProfileError(errorMessage: String)
    fun getProfileSuccess(data: List<ProfileEntity>)
}