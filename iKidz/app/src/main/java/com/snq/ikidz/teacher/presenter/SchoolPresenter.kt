package com.snq.ikidz.teacher.presenter

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.RESP.RESP_CountNotify
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.server.GetCountNotify
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.JsonHelper
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils
import com.snq.ikidz.teacher.view.fragment.school.ISchoolView

class SchoolPresenter(var view: ISchoolView) {
    val TAG = "SchoolPresenter"

    fun getCountNotify() {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        var classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        if (token != null && link_api != null) {
            object : GetCountNotify(token, link_api, classId, 2) {
                override fun onSuccess(notify: RESP_CountNotify?) {
                    iKidApplications.log(TAG, JsonHelper.toJson(notify!!))
                    view.getSetNotify(notify.data.data)
                }

                override fun onError(s: ErrorEntity) {
                    iKidApplications.log(TAG, s.errorMessage)
                }

            }
        }
    }
}