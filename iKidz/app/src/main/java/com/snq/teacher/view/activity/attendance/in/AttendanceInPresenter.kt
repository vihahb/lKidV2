package com.snq.teacher.view.activity.attendance.`in`

import com.snq.teacher.R
import com.snq.teacher.model.RESP.RESP_AttendanceIn
import com.snq.teacher.model.RESP.RESP_Status
import com.snq.teacher.model.entity.AttendanceIn
import com.snq.teacher.model.entity.DataUpdateAttendance
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.entity.ValueAttendance
import com.snq.teacher.model.server.GetAttendanceIn
import com.snq.teacher.model.server.UpdateAttendanceIn
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.NetworkUtils
import com.snq.teacher.sdk.Utils.SharedUtils
import com.snq.teacher.sdk.Utils.TimeUtils

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