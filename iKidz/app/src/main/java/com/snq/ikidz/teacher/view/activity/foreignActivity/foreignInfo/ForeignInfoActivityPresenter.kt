package com.snq.ikidz.teacher.view.activity.foreignActivity.foreignInfo

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.RESP.RESP_ForeignInfo
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.server.GetInfoForeign
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils

class ForeignInfoActivityPresenter(private var view: IGetForeignInfo) {

    var TAG = "ForeignInfoActivityPresenter"

    fun getForeignInfo(id: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        view.showProgressBar(true, true, "Loading...")

        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        object : GetInfoForeign(link_api, token, id) {
            override fun onSuccess(data: RESP_ForeignInfo?) {
                view.closeProgressBar()
                iKidApplications.log(TAG, "getAbsentByClass success: " + data.toString())
                view.getForeignInfoSuccess(data!!.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                iKidApplications.log(TAG, "getAbsentByClass onError: " + s.errorMessage)
                view.getForeignError(s.errorMessage)
            }

        }

    }
}