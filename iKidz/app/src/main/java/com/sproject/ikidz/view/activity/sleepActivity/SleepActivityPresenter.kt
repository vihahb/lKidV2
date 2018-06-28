package com.sproject.ikidz.view.activity.sleepActivity

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_DataSleep
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetSleepList
import com.sproject.ikidz.sdk.Utils.NetworkUtils

class SleepActivityPresenter(private var view: ISleepActivity) {

    fun getDataSleep(date: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        view.showProgressBar(true, true, "Loading...")
        object : GetSleepList(date) {
            override fun onSucces(list: RESP_DataSleep?) {
                view.closeProgressBar()
                view.getSleepData(list!!.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                view.getSleepDataError(s.errorMessage)
            }

        }
    }

}