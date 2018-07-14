package com.snq.ikidz.teacher.view.activity.school

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.RESP.RESP_DataNews
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.entity.RequestNews
import com.snq.ikidz.teacher.model.server.GetNews
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.JsonHelper
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils

class SchoolsPresenter(private var view: ISchoolsList) {

    var TAG = "SchoolsPresenter"

    fun getSchool(page: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        var classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        var newsData = RequestNews(1, classId)
        object : GetNews(page, token, linkApi, newsData) {
            override fun onSucces(news: RESP_DataNews) {
                iKidApplications.log(TAG, JsonHelper.toJson(news))
                view.getSchoolsSuccess(news.data.data, page)
            }

            override fun onError(s: ErrorEntity) {
                iKidApplications.log(TAG, s.errorMessage)
                view.getSchoolsError(s.errorMessage)
            }

        }
    }

}