package com.sproject.ikidz.view.activity.school.info

import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.entity.NewsEntity
import com.sproject.ikidz.model.entity.NewsInfo
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.Utils.WidgetUtils
import com.sproject.ikidz.view.base.BaseActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_schools_news_info.*

class SchoolsNewsInfo : BaseActivity(), ISchoolNewsInfo {

    var TAG = "SchoolsNewsInfo"

    override fun getSchoolInfoSuccess(data: NewsInfo) {
        if (!TextUtils.isEmpty(data.title))
            tv_title.text = data.title

        if (!TextUtils.isEmpty(data.content))
            tv_content.text = Html.fromHtml(data.content)

        info_user = "<font color=\'#25ADC2\'>Người đăng</font>"

        if (!TextUtils.isEmpty(data.fullName))
            info_user += "\n<font color=\'#D9000000\'>" + data.fullName + "</font>"
        else
            info_user += "\nTrống!"

        tv_info_user.text = Html.fromHtml(info_user)

        if (!TextUtils.isEmpty(data.countLike))
            tv_like.text = data.countLike + " lượt thích"
        if (!TextUtils.isEmpty(data.countComment))
            tv_comment.text = data.countComment + " bình luận"

        if (!TextUtils.isEmpty(data.image))
            Picasso.with(iKidApplications.context)
                    .load(data.image)
                    .noPlaceholder()
                    .error(R.drawable.ic_load_image_error)
                    .into(img_image, object : Callback {
                        override fun onSuccess() {
                            iKidApplications.log(TAG, "Load image success url: " + data.image.toString())
                        }

                        override fun onError() {
                            iKidApplications.log(TAG, "Load image error url: " + data.image.toString())
                        }
                    })

        if (!TextUtils.isEmpty(data.avatar)) {
            WidgetUtils.setImageURL(img_avatar, data.avatar, R.mipmap.ic_launcher_round)
        }
    }

    override fun getSchoolInfoError(errorMessage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
    }

    var info_user = ""
    lateinit var presenter: SchoolsNewsPresenter
    lateinit var news: NewsEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schools_news_info)
        initToolbar("NHÀ TRƯỜNG", true)
        presenter = SchoolsNewsPresenter(this)
        news = NewsEntity()
        getData()
    }

    private fun getData() {
        news = intent.getSerializableExtra(Constants.OBJECT) as NewsEntity
        presenter.getInfoNews(news.id)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
