package com.sproject.ikidz.view.activity.eatActivity

import com.sproject.ikidz.model.entity.DataEatEntity
import com.sproject.ikidz.model.entity.EatValue
import com.sproject.ikidz.model.entity.MenuEatEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface IEatActivity : IBasicActivity {
    fun getMenuEatSuccess(menuList: List<MenuEatEntity>)
    fun getEatActivitySuccess(eatList: DataEatEntity)
    fun getMenuEatError(errorMessage: String?)
    fun getEatActivityError(errorMessage: String?)
    fun updateEatValueSuccess(position: Int, data: EatValue)
    fun updateEatValueError()
}