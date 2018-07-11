package com.snq.teacher.view.activity.school.info

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.snq.teacher.R
import com.snq.teacher.iKidApplications
import com.snq.teacher.model.entity.*
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.TextUtils
import com.snq.teacher.sdk.Utils.WidgetUtils
import com.snq.teacher.view.activity.news.all.AdapterAllNews
import com.snq.teacher.view.base.BaseActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_schools_news_info.*
import java.util.ArrayList

class SchoolsNewsInfo : BaseActivity(), ISchoolNewsInfo {
    override fun GetCommentSuccess(data: List<CommentEntity>) {
        if (list.isNotEmpty())
        {
            list.clear()
        }
        list.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun GetCommentError(s: ErrorEntity?) {
        showLongToast("Có lỗi khi lấy dữ liệu bình luận!")
    }

    override fun commentSuccess(data: RespondCommentEntity?) {
        edt_comment.setText("")
        presenter.getComment(news.id, 1)
    }

    override fun commentError() {
        showLongToast("Bình luận không thành công!")
    }

    override fun getProfileSuccess(dataUser: DataUser) {
        info_user_avatar = dataUser.user.avatar
        if (!TextUtils.isEmpty(info_user_avatar)) {
            WidgetUtils.setImageURL(img_user_ava, info_user_avatar, R.mipmap.ic_launcher_round)
        }
    }

    var TAG = "SchoolsNewsInfo"

    override fun getSchoolInfoSuccess(data: NewsInfo) {
        if (!TextUtils.isEmpty(data.title))
            tv_title.text = data.title

        if (!TextUtils.isEmpty(data.content)) {
            webview.loadDataWithBaseURL("file:///android_asset/",data.content.replace("<img ", "<img style=\"width: 100% !important; height:auto; max-height: 250px; margin: 0 auto !important;\""), "text/html; charset=utf-8", "utf-8", null)
        }

        info_user = "<font color=\'#25ADC2\'>Người đăng</font>"

        info_user += if (!TextUtils.isEmpty(data.fullName))
            "<br></br><font color=\'#D9000000\'>" + data.fullName + "</font>"
        else
            "<br></br>Trống!"

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
    var info_user_avatar = ""
    lateinit var presenter: SchoolsNewsPresenter
    lateinit var news: NewsEntity
    lateinit var webSettings: WebSettings
    var page = 1
    lateinit var adapter: AdapterAllNews
    lateinit var list: ArrayList<CommentEntity>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schools_news_info)
        initToolbar("NHÀ TRƯỜNG", true)
        presenter = SchoolsNewsPresenter(this)
        news = NewsEntity()
        initView()
        getData()
    }

    private fun initView() {
        list = ArrayList()
        adapter = AdapterAllNews(list, this)
        rcl_comment.layoutManager = LinearLayoutManager(this)
        rcl_comment.adapter = adapter

        btnComment.setOnClickListener {
            if (valid()){
                presenter.sendComment(news.id, edt_comment.text.toString())
            }
        }
    }

    private fun valid(): Boolean {
        if (edt_comment.text.isNullOrEmpty()) {
            showLongToast("Vui lòng nhập nội dung bình luận!")
            return false
        }
        return true
    }

    private fun getData() {
        news = intent.getSerializableExtra(Constants.OBJECT) as NewsEntity
        presenter.getInfoNews(news.id)
        presenter.getComment(news.id, page)
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(viewx: WebView, urlx: String): Boolean {
                viewx.loadUrl(urlx)
                return true
            }
        }
        webSettings = webview.settings
        webSettings.defaultTextEncodingName = "utf-8"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
