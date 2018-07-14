package com.snq.teacher.view.activity.subscribeToEat.fragment

import com.snq.teacher.model.entity.EatTicketEntity
import com.snq.teacher.view.base.IBasicActivity

interface ISubscribeToEat : IBasicActivity {
    fun getDataTicketSuccess(detail_ticket: List<EatTicketEntity>)
    fun getDataTicketError(errorMessage: String)
}