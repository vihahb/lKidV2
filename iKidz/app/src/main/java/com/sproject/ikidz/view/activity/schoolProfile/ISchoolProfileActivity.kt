package com.sproject.ikidz.view.activity.schoolProfile

import com.sproject.ikidz.model.entity.ProfileEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface ISchoolProfileActivity : IBasicActivity {
    fun getProfileError(errorMessage: String)
    fun getProfileSuccess(data: List<ProfileEntity>)
}