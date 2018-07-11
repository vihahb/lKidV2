package com.snq.ikidz.teacher.view.activity.commentDay

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.RESP.RESP_CommentLearn
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.server.GetLearnCmtList
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils

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