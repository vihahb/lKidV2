package com.sproject.ikidz.view.activity.shuttleBus

import com.sproject.ikidz.model.entity.ShuttleBusEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface IShuttlesBus : IBasicActivity {
    fun getShuttleBusSuccess(data: MutableList<ShuttleBusEntity>)
    fun getShuttlesError(errorMessage: String)

}
