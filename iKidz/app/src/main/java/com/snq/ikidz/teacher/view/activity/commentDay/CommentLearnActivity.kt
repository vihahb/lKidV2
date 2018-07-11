package com.snq.ikidz.teacher.view.activity.commentDay

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.entity.CommentLearnEntity
import com.snq.ikidz.teacher.sdk.Utils.TextUtils
import com.snq.ikidz.teacher.sdk.Utils.TimeUtils
import com.snq.ikidz.teacher.sdk.callback.DatePickerListener
import com.snq.ikidz.teacher.sdk.callback.ItemClickListenerGeneric
import com.snq.ikidz.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_comment_learn.*

class CommentLearnActivity : BaseActivity(), ICommentLearn, SearchView.OnQueryTextListener {
    override fun getCommentLearnData(data: List<CommentLearnEntity>) {
        if (data.isEmpty()) {
            tv_message.visibility = View.VISIBLE
            tv_message.text = resources.getString(R.string.mesage_no_data)
        } else {
            tv_message.visibility = View.INVISIBLE
            if (list.isNotEmpty())
                list.clear()
            list.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }

    override fun getCommentLearnDataError(message: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = message
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty())
            adapter.filterList(list)
        else
            filterItem(newText!!)
        return true
    }

    lateinit var presenter: CommentLearnPresenter
    lateinit var list: ArrayList<CommentLearnEntity>
    lateinit var adapter: AdapterLearnCmt
    lateinit var date: String
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_learn)
        initToolbar("NHẬN XÉT NGÀY HỌC", true)
        presenter = CommentLearnPresenter(this)
        initView()
    }

    private fun initView() {

        date = TimeUtils.getCurrentTimeFormat("dd/MM/yyyy")
        edt_time.setText(date)
        edt_time.setOnClickListener {
            TimeUtils.getInstance().showDatePickerDialog(this, object : DatePickerListener {
                override fun onSelected(year: Int, month: Int, day: Int) {
                    date = "" + day + "/" + (month + 1) + "/" + year
                    edt_time.setText(date)
                    presenter.getCommentLearn(date)
                }

            })
        }
        list = ArrayList()
        adapter = AdapterLearnCmt(list, this, object : ItemClickListenerGeneric<CommentLearnEntity> {
            override fun ItemClick(id: Int, data: CommentLearnEntity?) {

            }
        })
        rcl_learn_comment.layoutManager = LinearLayoutManager(this)
        rcl_learn_comment.adapter = adapter
        presenter.getCommentLearn(date)
    }

    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<CommentLearnEntity>()

        for (s in list) {
            if (TextUtils.unicodeToKoDauLowerCase(s.fullName.toLowerCase()).contains(TextUtils.unicodeToKoDauLowerCase(name.toLowerCase()))) {
                filterdNames.add(s)
            }
        }
        adapter.filterList(filterdNames)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_attendance_out, menu)
        val itemSearch = menu!!.findItem(R.id.search_view_attendance_out)
        searchView = itemSearch.actionView as SearchView
        //set OnQueryTextListener cho search view để thực hiện search theo text
        searchView!!.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
