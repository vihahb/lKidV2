package com.sproject.ikidz.view.activity.attendance.`in`

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_AttendanceIn
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetAttendanceIn
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

class AttendanceInPresenter(private var view: IAttendanceIn) {

    fun getListAttendanceIn() {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        val classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)

        object : GetAttendanceIn(token, classId) {
            override fun onSuccess(data: RESP_AttendanceIn) {
                view.getAttendanceInSuccess(data.data)
            }

            override fun onError(s: ErrorEntity) {
                view.getAttendanceInError(s.errorMessage)
            }

        }
    }
}