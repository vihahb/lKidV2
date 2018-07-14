package com.snq.teacher.view.activity.eatActivity

import com.snq.teacher.R
import com.snq.teacher.model.RESP.RESP_DataEat
import com.snq.teacher.model.RESP.RESP_MenuEat
import com.snq.teacher.model.RESP.RESP_ResultValue
import com.snq.teacher.model.entity.EatValue
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.entity.UpdateValueEat
import com.snq.teacher.model.server.GetEatActivity
import com.snq.teacher.model.server.GetMenuList
import com.snq.teacher.model.server.UpdateEatEntity
import com.snq.teacher.sdk.Utils.NetworkUtils
import java.util.*

class EatActivityPresenter(private var view: IEatActivity) {
    fun getEatMenu(date: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        view.showProgressBar(true, true, "Loading...")
        object : GetMenuList(date) {
            override fun onSucces(respond: RESP_MenuEat?) {
                view.closeProgressBar()
                view.getMenuEatSuccess(respond!!.data)
                if (respond.data.isNotEmpty())
                    getEatActivity(date, respond.data[0].code)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.getMenuEatError(s!!.errorMessage)
            }

        }
    }

    fun getEatActivity(date: String, menu: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        object : GetEatActivity(date, menu) {
            override fun onSucces(respond: RESP_DataEat?) {
                view.getEatActivitySuccess(respond!!.data)
            }

            override fun onError(s: ErrorEntity?) {
                view.getEatActivityError(s!!.errorMessage)
            }

        }
    }

    fun updateEat(date: String, data: EatValue, position: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(true, true, "Đang cập nhật...")
        var arr = ArrayList<EatValue>()
        arr.add(data)
        var value = UpdateValueEat("eat", date, -1, arr)
        object : UpdateEatEntity(value) {
            override fun onSucces(respond: RESP_ResultValue?) {
                view.closeProgressBar()
                view.updateEatValueSuccess(position, data)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.showLongToast("Cập nhật thông tin không thành công!")
                view.updateEatValueError()
            }

        }
    }
}