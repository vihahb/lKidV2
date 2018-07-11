package com.snq.teacher.view.activity.sleepActivity

import com.snq.teacher.R
import com.snq.teacher.model.RESP.RESP_DataSleep
import com.snq.teacher.model.RESP.RESP_ResultValue
import com.snq.teacher.model.RESP.RESP_Status
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.entity.UpdateSleepAndOtherEntity
import com.snq.teacher.model.entity.ValueSleepEntity
import com.snq.teacher.model.server.GetSleepList
import com.snq.teacher.model.server.UpdateSleepChild
import com.snq.teacher.model.server.UpdateTimeSleep
import com.snq.teacher.sdk.Utils.NetworkUtils
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