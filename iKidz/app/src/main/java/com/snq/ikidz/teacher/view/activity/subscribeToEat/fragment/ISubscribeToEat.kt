package com.snq.ikidz.teacher.view.activity.subscribeToEat.fragment

import com.snq.ikidz.teacher.model.entity.EatTicketEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ISubscribeToEat : IBasicActivity {
    fun getDataTicketSuccess(detail_ticket: List<EatTicketEntity>)
    fun getDataTicketError(errorMessage: String)
}