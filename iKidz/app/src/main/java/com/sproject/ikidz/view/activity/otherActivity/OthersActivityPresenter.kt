package com.sproject.ikidz.view.activity.otherActivity

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_DataOtherActivity
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.ParamsOtherActivitysEntity
import com.sproject.ikidz.model.server.GetListOtherActivity
import com.sproject.ikidz.sdk.Utils.NetworkUtils

class OthersActivityPresenter(private var view: IOtherActivitysView) {
    fun getOthersList(date: String, start_time: String, end_time: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        var params = ParamsOtherActivitysEntity(date, start_time, end_time)

        view.showProgressBar(true, true, "Loading...")
        object : GetListOtherActivity(params) {
            override fun onSucces(data: RESP_DataOtherActivity) {
                view.closeProgressBar()
                view.getOthersList(data.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                view.getOthersListError(s.errorMessage)
            }
        }
    }
}