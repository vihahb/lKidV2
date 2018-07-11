package com.snq.teacher.view.activity.schoolProfile

import com.snq.teacher.R
import com.snq.teacher.model.RESP.RESP_DataProfile
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.entity.SendGetClassInfo
import com.snq.teacher.model.server.GetProfileModel
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.NetworkUtils
import com.snq.teacher.sdk.Utils.SharedUtils

class SchoolProfileActivityPresenter(private var view: ISchoolProfileActivity) {
    fun getSchoolProfile(page: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        var classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        var params = SendGetClassInfo(classId, 40, page, null, -1)


        view.showProgressBar(true, true, "Loading...")
        object : GetProfileModel(params, linkApi, token) {
            override fun onSuccess(profile: RESP_DataProfile) {
                view.closeProgressBar()
                view.getProfileSuccess(profile.data.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                view.getProfileError(s.errorMessage)
            }

        }
    }
}