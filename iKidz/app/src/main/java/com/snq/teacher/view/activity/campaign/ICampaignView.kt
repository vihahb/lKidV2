package com.snq.teacher.view.activity.campaign

import com.snq.teacher.model.entity.CampainEntity
import com.snq.teacher.view.base.IBasicActivity

interface ICampaignView : IBasicActivity {
    fun getcampaignListSuccess(data: List<CampainEntity>)
    fun getcampaignListEror(mesage: String)
}