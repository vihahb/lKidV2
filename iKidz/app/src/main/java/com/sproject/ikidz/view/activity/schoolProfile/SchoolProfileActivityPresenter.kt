package com.sproject.ikidz.view.activity.schoolProfile

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_DataProfile
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.SendGetClassInfo
import com.sproject.ikidz.model.server.GetProfileModel
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

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