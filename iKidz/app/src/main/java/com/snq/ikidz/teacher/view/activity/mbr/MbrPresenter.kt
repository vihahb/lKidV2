package com.snq.ikidz.teacher.view.activity.mbr

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.RESP.RESP_StudentEntity
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.server.GetMbrList
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils

class MbrPresenter(private var view: IMbr) {
    fun getMbr() {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(true, true, "Loading...")
        object : GetMbrList() {
            override fun onSucces(data: RESP_StudentEntity?) {
                view.closeProgressBar()
                view.getMrbSuccess(data!!.data.data)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.getMbrError(s!!.errorMessage)
            }
        }
    }
}