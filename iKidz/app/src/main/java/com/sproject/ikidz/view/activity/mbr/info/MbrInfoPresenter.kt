package com.sproject.ikidz.view.activity.mbr.info

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_MbrInfo
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetMbrInfo
import com.sproject.ikidz.sdk.Utils.NetworkUtils

class MbrInfoPresenter(private var view: IMbrInfoActivity) {
    fun getMbrInfo(id: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(true, true, "Loading...")
        object : GetMbrInfo(id) {
            override fun onSucces(data: RESP_MbrInfo?) {
                view.getMbrInfoSuccess(data!!.data)
                view.closeProgressBar()
            }

            override fun onError(s: ErrorEntity?) {
                view.getMbrError(s!!.errorMessage)
                view.closeProgressBar()
            }

        }
    }
}