package com.snq.teacher.view.activity.drug.teacher.fragment.presenter

import com.snq.teacher.R
import com.snq.teacher.iKidApplications
import com.snq.teacher.model.RESP.RESP_GetDrug
import com.snq.teacher.model.RESP.RESP_Status
import com.snq.teacher.model.entity.DrugEntity
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.entity.SendGetClassInfo
import com.snq.teacher.model.server.GetDrugModel
import com.snq.teacher.model.server.ValidDrug
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.NetworkUtils
import com.snq.teacher.sdk.Utils.SharedUtils
import com.snq.teacher.view.activity.drug.teacher.fragment.IDrugFragment

class DrugFragmentPre(var view: IDrugFragment) {
    var TAG = "AbsentFragmentPre"

    fun getDrugByClass(page: Int, type: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API)
        val class_id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)

        var getDrug = SendGetClassInfo(class_id, 0, page, null, type)

        object : GetDrugModel(getDrug, link_api, token) {
            override fun onSuccess(respond: RESP_GetDrug) {
                iKidApplications.log(TAG, "getAbsentByClass success: " + respond.toString())
                view.getDrugSuccess(respond.data.data)
            }

            override fun onError(s: ErrorEntity) {
                iKidApplications.log(TAG, "getAbsentByClass onError: " + s.errorMessage)
                view.getDrugError(s.errorMessage)
            }

        }
    }

    fun validDrug(data: DrugEntity, position: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(true, true, "Đang xác nhận đơn dặn thuốc...")
        object : ValidDrug(data.id) {
            override fun onSucces(news: RESP_Status?) {
                view.closeProgressBar()
                view.validDrugSuccess(position)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.validDrugError()
            }

        }
    }
}