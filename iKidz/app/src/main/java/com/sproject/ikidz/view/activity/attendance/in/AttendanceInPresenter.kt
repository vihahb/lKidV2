package com.sproject.ikidz.view.activity.attendance.`in`

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_AttendanceIn
import com.sproject.ikidz.model.RESP.RESP_Status
import com.sproject.ikidz.model.entity.AttendanceIn
import com.sproject.ikidz.model.entity.DataUpdateAttendance
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.ValueAttendance
import com.sproject.ikidz.model.server.GetAttendanceIn
import com.sproject.ikidz.model.server.UpdateAttendanceIn
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.sdk.Utils.TimeUtils

class AttendanceInPresenter(private var view: IAttendanceIn) {

    fun getListAttendanceIn(date: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(true, true, "Loading...")
        val classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)

        object : GetAttendanceIn(token, classId, date) {
            override fun onSuccess(data: RESP_AttendanceIn) {
                view.closeProgressBar()
                view.getAttendanceInSuccess(data.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                view.getAttendanceInError(s.errorMessage)
            }

        }
    }

    fun updateAttendanceInt(pos: Int, type: Int, data: AttendanceIn, date: String) {
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
        object : UpdateAttendanceIn(value) {
            override fun onSucces(news: RESP_Status?) {
                view.closeProgressBar()
                view.updateAttendanceSuccess(pos, type)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.updateAttendanceError()
            }

        }
    }

    fun updateAllAttendanceOut(type: Int, data: java.util.ArrayList<AttendanceIn>, date: String) {
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
        object : UpdateAttendanceIn(value) {
            override fun onSucces(news: RESP_Status?) {
                view.closeProgressBar()
                view.updateAttendanceSuccess(-1, type)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.updateAttendanceError()
            }

        }
    }
}