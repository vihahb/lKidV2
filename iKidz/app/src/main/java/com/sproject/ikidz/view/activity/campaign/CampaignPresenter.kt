package com.sproject.ikidz.view.activity.campaign

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_DataCampaign
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetDataCampaign
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

class CampaignPresenter(private var view: ICampaignView) {
    fun getCampaignSurvey() {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.BASE_URL)
        view.showProgressBar(true, true, "Loading...")
        object : GetDataCampaign(token, linkApi) {
            override fun onSucces(data: RESP_DataCampaign?) {
                view.closeProgressBar()
                view.getcampaignListSuccess(data!!.data.data)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.getcampaignListEror(s!!.errorMessage)
            }

        }
    }
}