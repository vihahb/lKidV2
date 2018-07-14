package com.snq.ikidz.teacher.view.activity.campaign

import com.snq.ikidz.teacher.model.entity.CampainEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ICampaignView : IBasicActivity {
    fun getcampaignListSuccess(data: List<CampainEntity>)
    fun getcampaignListEror(mesage: String)
}