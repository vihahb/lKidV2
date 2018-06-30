package com.sproject.ikidz.view.activity.eatActivity

import com.sproject.ikidz.model.entity.EatEntity
import com.sproject.ikidz.model.entity.MenuEatEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface IEatActivity : IBasicActivity {
    fun getMenuEatSuccess(menuList: List<MenuEatEntity>)
    fun getEatActivitySuccess(eatList: List<EatEntity>)
    fun getMenuEatError(errorMessage: String?)
    fun getEatActivityError(errorMessage: String?)
}