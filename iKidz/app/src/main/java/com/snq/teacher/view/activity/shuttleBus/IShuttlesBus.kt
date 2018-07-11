package com.snq.teacher.view.activity.shuttleBus

import com.snq.teacher.model.entity.ShuttleBusEntity
import com.snq.teacher.model.entity.ShuttlepickupPersonEntity
import com.snq.teacher.view.base.IBasicActivity

interface IShuttlesBus : IBasicActivity {
    fun getShuttleBusSuccess(data: MutableList<ShuttleBusEntity>)
    fun getShuttlesError(errorMessage: String)
    fun getShuttlesPersonSuccess(data: List<ShuttlepickupPersonEntity>)
    fun getShuttlesPersonError(errorMessage: String)

}
