package com.snq.ikidz.teacher.view.activity.eatActivity

import com.snq.ikidz.teacher.model.entity.DataEatEntity
import com.snq.ikidz.teacher.model.entity.EatValue
import com.snq.ikidz.teacher.model.entity.MenuEatEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface IEatActivity : IBasicActivity {
    fun getMenuEatSuccess(menuList: List<MenuEatEntity>)
    fun getEatActivitySuccess(eatList: DataEatEntity)
    fun getMenuEatError(errorMessage: String?)
    fun getEatActivityError(errorMessage: String?)
    fun updateEatValueSuccess(position: Int, data: EatValue)
    fun updateEatValueError()
}