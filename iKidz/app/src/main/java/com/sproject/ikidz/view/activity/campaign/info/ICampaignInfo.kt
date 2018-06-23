package com.sproject.ikidz.view.activity.campaign.info

import com.sproject.ikidz.model.entity.InfoCampaignEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface ICampaignInfo : IBasicActivity {
    fun getInfoCampaignSuccess(data: InfoCampaignEntity)
    fun getInfoCampaignError(errorMessage: String)
}