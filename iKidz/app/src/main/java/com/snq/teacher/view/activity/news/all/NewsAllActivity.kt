package com.snq.teacher.view.activity.news.all

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.snq.teacher.R
import com.snq.teacher.model.entity.CommentEntity
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.entity.NewsEntity
import com.snq.teacher.model.entity.RespondCommentEntity
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.SharedUtils
import com.snq.teacher.sdk.Utils.WidgetUtils
import com.snq.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_news_all.*
import java.util.*

class NewsAllActivity : BaseActivity(), INewsAll {
    override fun commentError() {
        showLongToast("Bình luận không thành công!")
    }

    override fun commentSuccess(data: RespondCommentEntity?) {
        edt_comment.setText("")
        presenter.getComment(entity.id, 1)
    }

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

    lateinit var presenter: NewsAllPresenter
    lateinit var adapter: AdapterAllNews
    lateinit var list: ArrayList<CommentEntity>
    lateinit var entity: NewsEntity
    var page = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_all)
        initToolbar("BÌNH LUẬN", true)
        initView()
        getData()
    }

    private fun initView() {
        presenter = NewsAllPresenter(this)
        list = ArrayList()
        adapter = AdapterAllNews(list, this)
        rcl_comment.layoutManager = LinearLayoutManager(this)
        rcl_comment.adapter = adapter

        btnComment.setOnClickListener {
            if (valid()){
                presenter.sendComment(entity.id, edt_comment.text.toString())
            }
        }

        WidgetUtils.setImageURL(img_avatar, SharedUtils.getInstance().getStringValue(Constants.AVATAR_USER), R.mipmap.ic_student_user)
    }

    private fun valid(): Boolean {
        if (edt_comment.text.isNullOrEmpty()) {
            showLongToast("Vui lòng nhập nội dung bình luận!")
            return false
        }
        return true
    }

    private fun getData() {
        entity = intent.getSerializableExtra(Constants.OBJECT) as NewsEntity

        tv_comment.text = entity.countComment + " bình luận"
        tv_like.text = entity.countLike + " lượt thích"

        presenter.getComment(entity.id, page)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
