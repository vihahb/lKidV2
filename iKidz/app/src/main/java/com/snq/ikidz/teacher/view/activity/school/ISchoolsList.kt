package com.snq.ikidz.teacher.view.activity.school

import com.snq.ikidz.teacher.model.entity.NewsEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ISchoolsList : IBasicActivity {
    fun getSchoolsSuccess(data: List<NewsEntity>, page: Int)
    fun getSchoolsError(errorMessage: String)
}