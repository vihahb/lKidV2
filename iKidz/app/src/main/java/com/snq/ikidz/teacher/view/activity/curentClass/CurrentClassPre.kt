package com.snq.ikidz.teacher.view.activity.curentClass

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.RESP.RESP_CurrentClass
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.entity.SendGetClassInfo
import com.snq.ikidz.teacher.model.server.GetCurrentClass
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils

class CurrentClassPre(private var view: ICurrentClass) {

    var TAG = "CurrentClassPre"

    fun getCurrentClass(page: Int) {

        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API)
        val class_id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)

        var getClass = SendGetClassInfo(class_id, 0, page, null, -1)

        object : GetCurrentClass(link_api, token, getClass) {
            override fun onSuccess(respond: RESP_CurrentClass) {
                iKidApplications.log(TAG, "getCurrentClass success: " + respond.toString())
                view.getCurrentClassSuccess(respond.data.data)
            }

            override fun onError(s: ErrorEntity) {
                iKidApplications.log(TAG, "getCurrentClass onError: " + s.errorMessage)
                view.getClassError(s.errorMessage)
            }

        }
    }
}