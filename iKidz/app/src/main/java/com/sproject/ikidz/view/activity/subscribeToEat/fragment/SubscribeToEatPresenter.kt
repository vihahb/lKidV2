package com.sproject.ikidz.view.activity.subscribeToEat.fragment

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_DataEatTicket
import com.sproject.ikidz.model.RESP.RESP_LogToEat
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.PostParamsEat
import com.sproject.ikidz.model.server.GetDataTicketToEat
import com.sproject.ikidz.model.server.GetLogTicketToEat
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.JsonHelper
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

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