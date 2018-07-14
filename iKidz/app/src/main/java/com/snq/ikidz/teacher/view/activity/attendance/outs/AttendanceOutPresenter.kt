package com.snq.ikidz.teacher.view.activity.attendance.outs

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.RESP.RESP_AttendanceOut
import com.snq.ikidz.teacher.model.RESP.RESP_Status
import com.snq.ikidz.teacher.model.entity.AttendanceOut
import com.snq.ikidz.teacher.model.entity.DataUpdateAttendance
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.entity.ValueAttendance
import com.snq.ikidz.teacher.model.server.GetAttendanceOut
import com.snq.ikidz.teacher.model.server.UpdateAttendanceOut
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils
import com.snq.ikidz.teacher.sdk.Utils.TimeUtils

//
class AttendanceOutPresenter(private var view: IAttendanceOutActivity) {
    fun getListAttendanceOut(date: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        val classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        view.showProgressBar(true, true, "Loading...")
        object : GetAttendanceOut(token, classId, date) {
            override fun onSuccess(data: RESP_AttendanceOut) {
                view.closeProgressBar()
                view.getAttendanceOutSuccess(data.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                view.getAttendanceOutError(s.errorMessage)
            }

        }
    }

    fun updateAttendanceOut(pos: Int, type: Int, data: AttendanceOut, date: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        var attendanceDate = TimeUtils.formatDate(date, "dd-MM-yyyy", "yyyy-MM-dd")

        var valueUpdate = ValueAttendance(data.id.toString(), type.toString())

        var arr = ArrayList<ValueAttendance>()
        arr.add(valueUpdate)
        var value = DataUpdateAttendance(arr, attendanceDate, -1)
        view.showProgressBar(true, true, "Đang cập nhật thông tin điểm danh...")
        object : UpdateAttendanceOut(value) {
            override fun onSucces(news: RESP_Status?) {
                view.closeProgressBar()
                view.updateAttendanceSuccess(pos, 3)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.updateAttendanceError()
            }

        }
    }

    fun updateAllAttendanceOut(type: Int, data: java.util.ArrayList<AttendanceOut>, date: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        var attendanceDate = TimeUtils.formatDate(date, "dd-MM-yyyy", "yyyy-MM-dd")

        var arr = ArrayList<ValueAttendance>()
        data.forEachIndexed { index, element ->
            var valueUpdate = ValueAttendance(data[index].id.toString(), type.toString())
            arr.add(valueUpdate)
        }

        var value = DataUpdateAttendance(arr, attendanceDate, -1)
        view.showProgressBar(true, true, "Đang cập nhật thông tin điểm danh...")
        object : UpdateAttendanceOut(value) {
            override fun onSucces(news: RESP_Status?) {
                view.closeProgressBar()
                view.updateAttendanceSuccess(-1, 3)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.updateAttendanceError()
            }

        }
    }
}