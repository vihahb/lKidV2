package com.sproject.ikidz.view.activity.sleepActivity

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_DataSleep
import com.sproject.ikidz.model.RESP.RESP_ResultValue
import com.sproject.ikidz.model.RESP.RESP_Status
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.UpdateSleepAndOtherEntity
import com.sproject.ikidz.model.entity.ValueSleepEntity
import com.sproject.ikidz.model.server.GetSleepList
import com.sproject.ikidz.model.server.UpdateSleepChild
import com.sproject.ikidz.model.server.UpdateTimeSleep
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import java.util.*

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

    fun updateTimeSleep(start_time: String, end_time: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        view.showProgressBar(true, true, "Đang cập nhật thời gian ngủ...")
        object : UpdateTimeSleep(start_time, end_time) {
            override fun onSucces(data: RESP_Status) {
                view.closeProgressBar()
                view.updateTimeSleepSuccess()
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.updateTimeSleepError()
            }

        }
    }

    fun updateChildSleep(date: String, childValue: ValueSleepEntity, pos: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        view.showProgressBar(true, true, "Đang cập nhật thông tin thời gian ngủ của bé...")

        var arr = ArrayList<ValueSleepEntity>()
        arr.add(childValue)
        var updateChild = UpdateSleepAndOtherEntity("sleep", date, -1, arr)

        object : UpdateSleepChild(updateChild) {
            override fun onSucces(res: RESP_ResultValue?) {
                view.closeProgressBar()
                view.updateChildSuccess(pos, childValue)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.updateChildError()
            }

        }
    }

}