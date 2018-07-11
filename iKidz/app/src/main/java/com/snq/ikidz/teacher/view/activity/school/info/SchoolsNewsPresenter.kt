package com.snq.ikidz.teacher.view.activity.school.info

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.RESP.RESP_GetComment
import com.snq.ikidz.teacher.model.RESP.RESP_NewsInfo
import com.snq.ikidz.teacher.model.RESP.RESP_PostComment
import com.snq.ikidz.teacher.model.database.GetObjectByKeyModel
import com.snq.ikidz.teacher.model.entity.DataUser
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.entity.GetCommentParamsEntity
import com.snq.ikidz.teacher.model.entity.PostCommentEntity
import com.snq.ikidz.teacher.model.server.GetCommentModel
import com.snq.ikidz.teacher.model.server.GetNewsInfo
import com.snq.ikidz.teacher.model.server.PostCommentModel
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils

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