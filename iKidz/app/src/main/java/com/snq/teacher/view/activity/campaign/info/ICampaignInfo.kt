package com.snq.teacher.view.activity.campaign.info

import com.snq.teacher.model.RESP.RESP_DataAnswer
import com.snq.teacher.model.entity.InfoCampaignEntity
import com.snq.teacher.model.entity.RespondResultCampaign
import com.snq.teacher.view.base.IBasicActivity

interface ICampaignInfo : IBasicActivity {
    fun getInfoCampaignSuccess(data: InfoCampaignEntity)
    fun getInfoCampaignError(errorMessage: String)
    fun sendQuestionSuccess(data: RESP_DataAnswer)
    fun sendQuestionError(s: String)
    fun getResultError(errorMessage: String)
    fun getResultSuccess(data: RespondResultCampaign)
}