package com.sproject.ikidz.view.activity.news.all

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_GetComment
import com.sproject.ikidz.model.RESP.RESP_PostComment
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.GetCommentParamsEntity
import com.sproject.ikidz.model.entity.PostCommentEntity
import com.sproject.ikidz.model.server.GetCommentModel
import com.sproject.ikidz.model.server.PostCommentModel
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils

class NewsAllPresenter(private var view: INewsAll) {
    fun getComment(id_post: Int, page: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        var params = GetCommentParamsEntity(id_post, Constants.TYPE_POST_NEWS, page)

        object : GetCommentModel(params) {
            override fun onSucces(obj: RESP_GetComment?) {
                view.GetCommentSuccess(obj!!.data[0].data)
            }

            override fun onError(s: ErrorEntity?) {
                view.GetCommentError(s)
            }

        }
    }

    fun sendComment(id: Int?, comment: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        var commentParams = PostCommentEntity(comment, id, Constants.TYPE_POST_NEWS)
        object : PostCommentModel(commentParams) {
            override fun onSucces(obj: RESP_PostComment?) {
                view.commentSuccess(obj!!.data)
            }

            override fun onError(s: ErrorEntity?) {
                    view.commentError()
            }

        }
    }
}