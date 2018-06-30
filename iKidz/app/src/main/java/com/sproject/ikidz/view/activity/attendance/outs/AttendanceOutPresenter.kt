package com.sproject.ikidz.view.activity.attendance.outs

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_AttendanceOut
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetAttendanceOut
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

//
class AttendanceOutPresenter(private var view: IAttendanceOutActivity) {
    fun getListAttendanceOut() {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        val classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)

        object : GetAttendanceOut(token, classId) {
            override fun onSuccess(data: RESP_AttendanceOut) {
                view.getAttendanceOutSuccess(data.data)
            }

            override fun onError(s: ErrorEntity) {
                view.getAttendanceOutError(s.errorMessage)
            }

        }
    }
}