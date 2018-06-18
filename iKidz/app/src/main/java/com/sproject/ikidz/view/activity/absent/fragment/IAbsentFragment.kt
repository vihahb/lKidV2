package com.sproject.ikidz.view.activity.absent.fragment

import com.sproject.ikidz.model.entity.AbsentEntity

interface IAbsentFragment {
    fun getAbsentSuccess(data: List<AbsentEntity>)
    fun absentError(errorMessage: String)
}