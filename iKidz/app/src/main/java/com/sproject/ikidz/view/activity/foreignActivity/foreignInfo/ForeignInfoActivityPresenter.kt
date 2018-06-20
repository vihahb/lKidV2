package com.sproject.ikidz.view.activity.foreignActivity.foreignInfo

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_ForeignInfo
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetInfoForeign
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

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