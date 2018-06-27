package com.sproject.ikidz.view.activity.mbr

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_StudentEntity
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetMbrList
import com.sproject.ikidz.sdk.Utils.NetworkUtils

class MbrPresenter(private var view: IMbr) {
    fun getMbr(){
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