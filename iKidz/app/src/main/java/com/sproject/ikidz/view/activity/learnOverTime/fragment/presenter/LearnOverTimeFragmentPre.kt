package com.sproject.ikidz.view.activity.learnOverTime.fragment.presenter

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_LearnOverTime
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.SendGetClassInfo
import com.sproject.ikidz.model.server.GetLearnOverTimeModel
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.view.activity.learnOverTime.fragment.ILearnOverTimeFragment

class LearnOverTimeFragmentPre(var view: ILearnOverTimeFragment) {
    var TAG = "AbsentFragmentPre"

    fun getMoreTimeByClass(page: Int, type: Int) {

        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API)
        val class_id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)

        var getDrug = SendGetClassInfo(class_id, 0, page, null, type)

        object : GetLearnOverTimeModel(getDrug, link_api, token) {
            override fun onSuccess(respond: RESP_LearnOverTime) {
                iKidApplications.log(TAG, "getAbsentByClass success: " + respond.toString())
                view.getMoreTimeSuccess(respond.data.data)
            }

            override fun onError(s: ErrorEntity) {
                iKidApplications.log(TAG, "getAbsentByClass onError: " + s.errorMessage)
                view.getMoreTimeError(s.errorMessage)
            }

        }

    }
}