package com.sproject.ikidz.view.activity.campaign.info

import com.sproject.ikidz.model.RESP.RESP_DataAnswer
import com.sproject.ikidz.model.entity.InfoCampaignEntity
import com.sproject.ikidz.model.entity.RespondResultCampaign
import com.sproject.ikidz.view.base.IBasicActivity

interface ICampaignInfo : IBasicActivity {
    fun getInfoCampaignSuccess(data: InfoCampaignEntity)
    fun getInfoCampaignError(errorMessage: String)
    fun sendQuestionSuccess(data: RESP_DataAnswer)
    fun sendQuestionError(s: String)
    fun getResultError(errorMessage: String)
    fun getResultSuccess(data: RespondResultCampaign)
}