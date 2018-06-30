package com.sproject.ikidz.view.activity.absent.fragment

import com.sproject.ikidz.model.entity.AbsentEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface IAbsentFragment : IBasicActivity {
    fun getAbsentSuccess(data: List<AbsentEntity>)
    fun absentError(errorMessage: String)
    fun validAbsenSuccess(position: Int, state: Int)
    fun validAbsenError()
}