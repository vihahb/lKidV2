package com.sproject.ikidz.view.activity.curentClass

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_CurrentClass
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.SendGetClassInfo
import com.sproject.ikidz.model.server.GetCurrentClass
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

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