package com.sproject.ikidz.view.activity.schoolProfile.info

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_ProfileInfoEntity
import com.sproject.ikidz.model.RESP.RESP_SampleReview
import com.sproject.ikidz.model.RESP.RESP_Status
import com.sproject.ikidz.model.entity.DataProfilePostEntify
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetProfile
import com.sproject.ikidz.model.server.GetSampleReview
import com.sproject.ikidz.model.server.UpdateProfile
import com.sproject.ikidz.sdk.Utils.NetworkUtils

class ProfilePresenter(private var view: IProfileView) {

    init {
        getSampleReview()
    }

    private fun getSampleReview() {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        object : GetSampleReview(4) {
            override fun onSucces(res: RESP_SampleReview?) {
                view.getSampleReviewSuccess(res!!.data)
            }

            override fun onError(s: ErrorEntity?) {
                view.getSampleReviewError()
            }

        }
    }

    fun getProfile(id: Int?) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        view.showProgressBar(true, true, "Loading...")
        object : GetProfile(id!!) {
            override fun onSucces(data: RESP_ProfileInfoEntity?) {
                view.closeProgressBar()
                view.getProfileSuccess(data!!.data)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.getProfileError()
            }

        }
    }

    fun postProfile(data: DataProfilePostEntify) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        view.showProgressBar(true, true, "Đang cập nhật thông tin học bạ...")
        object : UpdateProfile(data) {
            override fun onSucces(res: RESP_Status?) {
                view.closeProgressBar()
                view.updateProfileSuccess()
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.updateProfileError()
            }

        }
    }
}