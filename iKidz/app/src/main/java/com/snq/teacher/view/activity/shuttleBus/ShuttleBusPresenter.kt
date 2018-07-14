package com.snq.teacher.view.activity.shuttleBus

import com.snq.teacher.R
import com.snq.teacher.iKidApplications
import com.snq.teacher.model.RESP.RESP_ShuttlePickupPerson
import com.snq.teacher.model.RESP.RESP_ShuttlesBus
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.server.GetShuttleBus
import com.snq.teacher.model.server.GetShuttlePickerPerson
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.JsonHelper
import com.snq.teacher.sdk.Utils.NetworkUtils
import com.snq.teacher.sdk.Utils.SharedUtils

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