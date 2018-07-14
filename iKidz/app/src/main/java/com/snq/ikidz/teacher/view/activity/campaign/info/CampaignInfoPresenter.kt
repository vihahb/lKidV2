package com.snq.ikidz.teacher.view.activity.campaign.info

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.RESP.RESP_DataAnswer
import com.snq.ikidz.teacher.model.RESP.RESP_InfoCampagn
import com.snq.ikidz.teacher.model.RESP.RESP_ResultValue
import com.snq.ikidz.teacher.model.entity.AnswerParams
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.server.GetInfoCampaign
import com.snq.ikidz.teacher.model.server.GetResult
import com.snq.ikidz.teacher.model.server.SendQuestion
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils

class CampaignInfoPresenter(private var view: ICampaignInfo) {
    fun getInfoCampaign(question_id: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.BASE_URL)
        view.showProgressBar(true, true, "Loading...")
        object : GetInfoCampaign(question_id, token, linkApi) {
            override fun onSucces(data: RESP_InfoCampagn) {
                view.closeProgressBar()
                view.getInfoCampaignSuccess(data.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                view.getInfoCampaignError(s.errorMessage)
            }

        }
    }

    fun sendQuestion(valueText: String, poll_id: Int, question_id: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.BASE_URL)
        view.showProgressBar(true, true, "Đang gửi câu trả lời...")

        var param = AnswerParams(question_id, valueText, poll_id)

        object : SendQuestion(param, token, linkApi) {
            override fun onSucces(data: RESP_DataAnswer) {
                view.closeProgressBar()
                view.sendQuestionSuccess(data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                if (s.errorCode == 37) {
                    view.sendQuestionError("Đã hết thời gian thăm dò ý kiến!")
                } else {
                    view.sendQuestionError(s.errorMessage)
                }

            }

        }
    }

    fun getResult(question_id: Int) {

        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.BASE_URL)
        view.showProgressBar(true, true, "Đang lấy dữ liệu câu trả lời...")


        object : GetResult(question_id, token, linkApi) {
            override fun onSucces(data: RESP_ResultValue) {
                view.closeProgressBar()
                view.getResultSuccess(data.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                view.getResultError(s.errorMessage)
            }

        }
    }
}