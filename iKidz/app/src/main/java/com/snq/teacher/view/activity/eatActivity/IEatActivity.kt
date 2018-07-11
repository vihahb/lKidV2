package com.snq.teacher.view.activity.eatActivity

import com.snq.teacher.model.entity.DataEatEntity
import com.snq.teacher.model.entity.EatValue
import com.snq.teacher.model.entity.MenuEatEntity
import com.snq.teacher.view.base.IBasicActivity

interface IEatActivity : IBasicActivity {
    fun getMenuEatSuccess(menuList: List<MenuEatEntity>)
    fun getEatActivitySuccess(eatList: DataEatEntity)
    fun getMenuEatError(errorMessage: String?)
    fun getEatActivityError(errorMessage: String?)
    fun updateEatValueSuccess(position: Int, data: EatValue)
    fun updateEatValueError()
}