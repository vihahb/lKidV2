package com.snq.teacher.view.activity.schoolProfile

import com.snq.teacher.model.entity.ProfileEntity
import com.snq.teacher.view.base.IBasicActivity

interface ISchoolProfileActivity : IBasicActivity {
    fun getProfileError(errorMessage: String)
    fun getProfileSuccess(data: List<ProfileEntity>)
}