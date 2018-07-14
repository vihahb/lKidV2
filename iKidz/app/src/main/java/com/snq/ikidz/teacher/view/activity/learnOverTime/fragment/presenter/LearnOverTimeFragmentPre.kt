package com.snq.ikidz.teacher.view.activity.learnOverTime.fragment.presenter

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.RESP.RESP_LearnOverTime
import com.snq.ikidz.teacher.model.RESP.RESP_Status
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.entity.LearnOverTimeEntity
import com.snq.ikidz.teacher.model.entity.SendGetClassInfo
import com.snq.ikidz.teacher.model.server.GetLearnOverTimeModel
import com.snq.ikidz.teacher.model.server.UpdateTimePick
import com.snq.ikidz.teacher.model.server.ValidOT
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils
import com.snq.ikidz.teacher.view.activity.learnOverTime.fragment.ILearnOverTimeFragment

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

    fun validOverTime(data: LearnOverTimeEntity, position: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(true, true, "Đang xác nhận đơn xin học thêm giờ...")
        object : ValidOT(data.id.toInt()) {
            override fun onSucces(news: RESP_Status?) {
                view.closeProgressBar()
                view.validOTSuccess(position)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.validOTError()
            }

        }
    }

    fun updateTimePick(id: String, timePickReturn: String, position: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(true, true, "Đang cập nhật thời gian trả trẻ...")
        object : UpdateTimePick(id.toInt(), timePickReturn) {
            override fun onSucces(news: RESP_Status?) {
                view.closeProgressBar()
                view.updateTimePickSuccess(position, timePickReturn)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.updateTimePickError()
            }

        }
    }
}