package com.snq.teacher.view.activity.school

import com.snq.teacher.model.entity.NewsEntity
import com.snq.teacher.view.base.IBasicActivity

interface ISchoolsList : IBasicActivity {
    fun getSchoolsSuccess(data: List<NewsEntity>, page: Int)
    fun getSchoolsError(errorMessage: String)
}