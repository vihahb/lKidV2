package com.sproject.ikidz.view.activity.school

import com.sproject.ikidz.model.RESP.RESP_DataNews
import com.sproject.ikidz.model.entity.NewsEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface ISchoolsList : IBasicActivity {
    fun getSchoolsSuccess(data: List<NewsEntity>, page: Int)
    fun getSchoolsError(errorMessage: String)
}