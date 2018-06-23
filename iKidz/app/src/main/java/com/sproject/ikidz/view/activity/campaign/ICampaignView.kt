package com.sproject.ikidz.view.activity.campaign

import com.sproject.ikidz.model.entity.CampainEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface ICampaignView : IBasicActivity {
    fun getcampaignListSuccess(data:List<CampainEntity>)
    fun getcampaignListEror(mesage: String)
}