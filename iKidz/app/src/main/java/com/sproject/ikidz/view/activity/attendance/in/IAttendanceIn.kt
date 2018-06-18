package com.sproject.ikidz.view.activity.attendance.`in`

import com.sproject.ikidz.model.entity.AttendanceIn
import com.sproject.ikidz.view.base.IBasicActivity

interface IAttendanceIn: IBasicActivity {
    fun getAttendanceInSuccess(data: List<AttendanceIn>)
    fun getAttendanceInError(s: String?)
}