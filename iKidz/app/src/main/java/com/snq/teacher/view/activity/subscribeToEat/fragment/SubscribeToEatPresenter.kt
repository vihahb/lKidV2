package com.snq.teacher.view.activity.subscribeToEat.fragment

import com.snq.teacher.R
import com.snq.teacher.iKidApplications
import com.snq.teacher.model.RESP.RESP_DataEatTicket
import com.snq.teacher.model.RESP.RESP_LogToEat
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.entity.PostParamsEat
import com.snq.teacher.model.server.GetDataTicketToEat
import com.snq.teacher.model.server.GetLogTicketToEat
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.JsonHelper
import com.snq.teacher.sdk.Utils.NetworkUtils
import com.snq.teacher.sdk.Utils.SharedUtils

class SubscribeToEatPresenter(private var view: ISubscribeToEat) {
    var TAG = "ShuttleBusPresenter"
    fun getSubscribeTicket() {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        var classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.LINK_API)
        view.showProgressBar(true, true, "Loading...")
        object : GetDataTicketToEat(classId, token, linkApi) {
            override fun onSucces(data: RESP_DataEatTicket) {
                view.closeProgressBar()
                iKidApplications.log(TAG, JsonHelper.toJson(data))
                view.getDataTicketSuccess(data.data.detail_ticket)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                iKidApplications.log(TAG, s.errorMessage)
                view.getDataTicketError(s.errorMessage)
            }

        }
    }

    fun getLogSubscribeTicket(month: Int, year: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        var classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        var postParams = PostParamsEat(classId, month, year)

        view.showProgressBar(true, true, "Loading...")
        object : GetLogTicketToEat(postParams, token, linkApi) {
            override fun onSucces(data: RESP_LogToEat) {
                view.closeProgressBar()
                iKidApplications.log(TAG, JsonHelper.toJson(data))
                view.getDataTicketSuccess(data.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                iKidApplications.log(TAG, s.errorMessage)
                view.getDataTicketError(s.errorMessage)
            }

        }
    }

}