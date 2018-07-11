package com.snq.ikidz.teacher.view.activity.absent.fragment

import com.snq.ikidz.teacher.model.entity.AbsentEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface IAbsentFragment : IBasicActivity {
    fun getAbsentSuccess(data: List<AbsentEntity>)
    fun absentError(errorMessage: String)
    fun validAbsenSuccess(position: Int, state: Int)
    fun validAbsenError()
}