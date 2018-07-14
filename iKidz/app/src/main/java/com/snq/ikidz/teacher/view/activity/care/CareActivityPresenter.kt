package com.snq.ikidz.teacher.view.activity.care

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.RESP.RESP_DataCareNews
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.server.GetCareNewsList
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils

class CareActivityPresenter(private var view: ICareActivity) {
    fun getCareNews() {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        view.showProgressBar(true, true, "Loading...")
        object : GetCareNewsList() {
            override fun onSucces(respond: RESP_DataCareNews) {
                view.closeProgressBar()
                view.getCareNewsSuccess(respond)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                view.getCareNewsError(s.errorMessage)
            }
        }
    }
}