package com.sproject.ikidz.view.activity.commentDay

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_CommentLearn
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetLearnCmtList
import com.sproject.ikidz.sdk.Utils.NetworkUtils

class CommentLearnPresenter(private var view: ICommentLearn) {
    fun getCommentLearn(date: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        view.showProgressBar(true, true, "Loading...")
        object : GetLearnCmtList(date) {
            override fun onSucces(res: RESP_CommentLearn?) {
                view.closeProgressBar()
                view.getCommentLearnData(res!!.data)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.getCommentLearnDataError(s!!.errorMessage)
            }

        }
    }
}