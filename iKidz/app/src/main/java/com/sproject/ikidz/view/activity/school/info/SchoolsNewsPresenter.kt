package com.sproject.ikidz.view.activity.school.info

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_NewsInfo
import com.sproject.ikidz.model.database.GetObjectByKeyModel
import com.sproject.ikidz.model.entity.DataUser
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetNewsInfo
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

class SchoolsNewsPresenter(private var view: ISchoolNewsInfo) {

    var TAG = "SchoolsNewsPresenter"

    init {
        getUser()
    }


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

    private fun getUser() {
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        if (token != null) {
            object : GetObjectByKeyModel<DataUser>(DataUser::class.java, "token", token) {
                override fun onError(message: ErrorEntity?) {
                    iKidApplications.log(TAG, "getUser: " + message!!.errorMessage)
                }

                override fun onSuccess(`object`: DataUser?) {
                    if (`object` != null) {
                        view.getProfileSuccess(`object`)
                    }
                }
            }
        }
    }
}