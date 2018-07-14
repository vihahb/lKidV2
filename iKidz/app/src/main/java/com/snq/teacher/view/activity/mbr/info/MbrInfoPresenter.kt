package com.snq.teacher.view.activity.mbr.info

import com.snq.teacher.R
import com.snq.teacher.model.RESP.RESP_MbrInfo
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.server.GetMbrInfo
import com.snq.teacher.sdk.Utils.NetworkUtils

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