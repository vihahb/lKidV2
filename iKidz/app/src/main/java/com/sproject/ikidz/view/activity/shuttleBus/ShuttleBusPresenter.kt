package com.sproject.ikidz.view.activity.shuttleBus

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_ShuttlePickupPerson
import com.sproject.ikidz.model.RESP.RESP_ShuttlesBus
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetShuttleBus
import com.sproject.ikidz.model.server.GetShuttlePickerPerson
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.JsonHelper
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

class ShuttleBusPresenter(private var view: IShuttlesBus) {
    var TAG = "ShuttleBusPresenter"
    fun getShuttleBus() {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        var classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        object : GetShuttleBus(classId, token, linkApi) {
            override fun onSucces(shuttles: RESP_ShuttlesBus) {
                iKidApplications.log(TAG, JsonHelper.toJson(shuttles))
                view.getShuttleBusSuccess(shuttles.data)
            }

            override fun onError(s: ErrorEntity) {
                iKidApplications.log(TAG, s.errorMessage)
                view.getShuttlesError(s.errorMessage)
            }

        }
    }

    fun getShuttlePerson(studentId: String?) {

        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        var id = studentId!!.toInt()

        object : GetShuttlePickerPerson(id, token, linkApi) {
            override fun onSucces(data: RESP_ShuttlePickupPerson) {
                iKidApplications.log(TAG, JsonHelper.toJson(data))
                view.getShuttlesPersonSuccess(data.data)
            }

            override fun onError(s: ErrorEntity) {
                iKidApplications.log(TAG, s.errorMessage)
                view.getShuttlesPersonError(s.errorMessage)
            }

        }
    }
}