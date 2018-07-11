package com.snq.ikidz.teacher.view.activity.attendance.outs

import com.snq.ikidz.teacher.model.entity.AttendanceOut
import com.snq.ikidz.teacher.view.base.IBasicActivity

//
interface IAttendanceOutActivity : IBasicActivity {

    fun getAttendanceOutSuccess(data: MutableList<AttendanceOut>)
    fun getAttendanceOutError(s: String?)
    fun updateAttendanceSuccess(pos: Int, checked: Int?)
    fun updateAttendanceError()

}