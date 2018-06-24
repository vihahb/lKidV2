package com.sproject.ikidz.view.activity.campaign.info

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_DataAnswer
import com.sproject.ikidz.model.RESP.RESP_InfoCampagn
import com.sproject.ikidz.model.RESP.RESP_ResultValue
import com.sproject.ikidz.model.entity.AnswerParams
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetInfoCampaign
import com.sproject.ikidz.model.server.GetResult
import com.sproject.ikidz.model.server.SendQuestion
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

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
                if (s.errorCode == 37){
                    view.sendQuestionError("Đã hết thời gian thăm dò ý kiến!")
                } else{
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