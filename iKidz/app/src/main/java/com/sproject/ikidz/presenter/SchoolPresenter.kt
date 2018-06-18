package com.sproject.ikidz.presenter

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_CountNotify
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetCountNotify
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.JsonHelper
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.view.fragment.school.ISchoolView

class SchoolPresenter(var view: ISchoolView) {
    val  TAG = "SchoolPresenter"

    fun getCountNotify() {
        if (!NetworkUtils.isConnected(view.activity)){
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        var classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        if (token != null && link_api != null){
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