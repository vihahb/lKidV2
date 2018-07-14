package com.snq.teacher.view.activity.news.all

import com.snq.teacher.R
import com.snq.teacher.model.RESP.RESP_GetComment
import com.snq.teacher.model.RESP.RESP_PostComment
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.entity.GetCommentParamsEntity
import com.snq.teacher.model.entity.PostCommentEntity
import com.snq.teacher.model.server.GetCommentModel
import com.snq.teacher.model.server.PostCommentModel
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.NetworkUtils

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