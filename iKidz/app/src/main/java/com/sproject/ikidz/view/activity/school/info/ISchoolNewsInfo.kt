package com.sproject.ikidz.view.activity.school.info

import com.sproject.ikidz.model.entity.NewsInfo
import com.sproject.ikidz.view.base.IBasicActivity

interface ISchoolNewsInfo : IBasicActivity {
    fun getSchoolInfoSuccess(data: NewsInfo)
    fun getSchoolInfoError(errorMessage: String)
}