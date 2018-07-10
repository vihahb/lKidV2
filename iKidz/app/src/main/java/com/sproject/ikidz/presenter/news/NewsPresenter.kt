package com.sproject.ikidz.presenter.news

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_DataNews
import com.sproject.ikidz.model.RESP.RESP_like
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.RequestNews
import com.sproject.ikidz.model.entity.SendLikePost
import com.sproject.ikidz.model.server.GetNews
import com.sproject.ikidz.model.server.LikePostModel
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.JsonHelper
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.view.fragment.news.NewsInf

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