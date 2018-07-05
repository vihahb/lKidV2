package com.sproject.ikidz.view.activity.attendance.outs

import com.sproject.ikidz.model.entity.AttendanceOut
import com.sproject.ikidz.view.base.IBasicActivity

//
interface IAttendanceOutActivity : IBasicActivity {

    fun getAttendanceOutSuccess(data: MutableList<AttendanceOut>)
    fun getAttendanceOutError(s: String?)
    fun updateAttendanceSuccess(pos: Int, checked: Int?)
    fun updateAttendanceError()

}