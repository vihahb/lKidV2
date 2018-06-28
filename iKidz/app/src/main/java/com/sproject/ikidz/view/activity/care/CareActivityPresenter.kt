package com.sproject.ikidz.view.activity.care

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_DataCareNews
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetCareNewsList
import com.sproject.ikidz.sdk.Utils.NetworkUtils

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