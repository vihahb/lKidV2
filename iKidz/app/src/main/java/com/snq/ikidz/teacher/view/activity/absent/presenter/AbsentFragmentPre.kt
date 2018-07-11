package com.snq.ikidz.teacher.view.activity.absent.presenter

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.RESP.RESP_GetAbsent
import com.snq.ikidz.teacher.model.RESP.RESP_Status
import com.snq.ikidz.teacher.model.entity.AbsentEntity
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.entity.SendGetClassInfo
import com.snq.ikidz.teacher.model.server.GetAbsentModel
import com.snq.ikidz.teacher.model.server.ValidAbsent
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils
import com.snq.ikidz.teacher.view.activity.absent.fragment.IAbsentFragment

class AbsentFragmentPre(var view: IAbsentFragment) {
    var TAG = "AbsentFragmentPre"

    fun getAbsentByClass(page: Int, type: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API)
        val class_id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)

        var getAbsent = SendGetClassInfo(class_id, 0, page, null, type)

        object : GetAbsentModel(getAbsent, link_api, token) {
            override fun onSuccess(respond: RESP_GetAbsent) {
                iKidApplications.log(TAG, "getAbsentByClass success: " + respond.toString())
                view.getAbsentSuccess(respond.data.data)
            }

            override fun onError(s: ErrorEntity) {
                iKidApplications.log(TAG, "getAbsentByClass onError: " + s.errorMessage)
                view.absentError(s.errorMessage)
            }

        }

    }

    fun validAbsent(data: AbsentEntity, position: Int, state: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(true, true, "Đang xác nhận đơn xin nghỉ...")
        object : ValidAbsent(data.id.toInt(), state) {
            override fun onSucces(news: RESP_Status?) {
                view.closeProgressBar()
                view.validAbsenSuccess(position, state)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.validAbsenError()
            }

        }
    }
}