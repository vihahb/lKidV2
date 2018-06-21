package com.sproject.ikidz.view.activity.school.info

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_NewsInfo
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetNewsInfo
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

class SchoolsNewsPresenter(private var view: ISchoolNewsInfo) {

    fun getInfoNews(id: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(true, true, "Loading...")
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        object : GetNewsInfo(id, token, linkApi) {
            override fun onSucces(news: RESP_NewsInfo?) {
                view.closeProgressBar()
                view.getSchoolInfoSuccess(news!!.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                view.getSchoolInfoError(s.errorMessage)
            }
        }
    }
}