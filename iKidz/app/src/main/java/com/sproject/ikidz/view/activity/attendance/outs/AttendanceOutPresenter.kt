package com.sproject.ikidz.view.activity.attendance.outs

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_AttendanceOut
import com.sproject.ikidz.model.RESP.RESP_Status
import com.sproject.ikidz.model.entity.AttendanceOut
import com.sproject.ikidz.model.entity.DataUpdateAttendance
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.ValueAttendance
import com.sproject.ikidz.model.server.GetAttendanceOut
import com.sproject.ikidz.model.server.UpdateAttendanceOut
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.sdk.Utils.TimeUtils

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