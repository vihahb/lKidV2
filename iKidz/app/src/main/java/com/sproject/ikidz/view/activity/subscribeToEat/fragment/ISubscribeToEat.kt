package com.sproject.ikidz.view.activity.subscribeToEat.fragment

import com.sproject.ikidz.model.entity.EatTicketEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface ISubscribeToEat : IBasicActivity {
    fun getDataTicketSuccess(detail_ticket: List<EatTicketEntity>)
    fun getDataTicketError(errorMessage: String)
}