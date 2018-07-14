package com.snq.ikidz.teacher.view.activity.attendance.`in`

import com.snq.ikidz.teacher.model.entity.AttendanceIn
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface IAttendanceIn : IBasicActivity {
    fun getAttendanceInSuccess(data: List<AttendanceIn>)
    fun getAttendanceInError(s: String?)
    fun updateAttendanceSuccess(pos: Int, checked: Int?)
    fun updateAttendanceError()
}