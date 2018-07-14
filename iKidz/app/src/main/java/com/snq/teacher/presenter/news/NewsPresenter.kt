package com.snq.teacher.presenter.news

import com.snq.teacher.R
import com.snq.teacher.iKidApplications
import com.snq.teacher.model.RESP.RESP_DataNews
import com.snq.teacher.model.RESP.RESP_like
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.entity.RequestNews
import com.snq.teacher.model.entity.SendLikePost
import com.snq.teacher.model.server.GetNews
import com.snq.teacher.model.server.LikePostModel
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.JsonHelper
import com.snq.teacher.sdk.Utils.NetworkUtils
import com.snq.teacher.sdk.Utils.SharedUtils
import com.snq.teacher.view.fragment.news.NewsInf

class NewsPresenter(val view: NewsInf) {

    var TAG = "NewsPresenter"

    fun getNews(page: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        var classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        var newsData = RequestNews(0, classId)
        object : GetNews(page, token, linkApi, newsData) {
            override fun onSucces(news: RESP_DataNews) {
                iKidApplications.log(TAG, JsonHelper.toJson(news))
                view.GetNewsSuccess(news, page)
            }

            override fun onError(s: ErrorEntity) {
                iKidApplications.log(TAG, s.errorMessage)
                view.GetNewsError(s.errorMessage)
            }
        }
    }

    fun likePost(pos: Int, post_id: Int){
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        var like = SendLikePost(post_id, Constants.LIKE_POST)

        object : LikePostModel(like) {
            override fun onSucces(obj: RESP_like?) {
                view.likeSuccess(obj, pos)
            }

            override fun onError(s: ErrorEntity?) {
                view.likeError(s)
            }

        }

    }

}