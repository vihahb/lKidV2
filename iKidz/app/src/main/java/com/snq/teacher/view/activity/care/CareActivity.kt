package com.snq.teacher.view.activity.care

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.snq.teacher.R
import com.snq.teacher.model.RESP.RESP_DataCareNews
import com.snq.teacher.model.entity.CareNewsEntity
import com.snq.teacher.sdk.Utils.TextUtils
import com.snq.teacher.sdk.callback.ItemClickListenerGeneric
import com.snq.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_care.*
import java.util.*

class CareActivity : BaseActivity(), SearchView.OnQueryTextListener, ICareActivity {
    override fun getCareNewsSuccess(respond: RESP_DataCareNews) {

        if (respond.data.data.isEmpty()) {
            tv_message.visibility = View.VISIBLE
            tv_message.text = resources.getString(R.string.mesage_no_data)
        } else {
            tv_message.visibility = View.GONE
            if (list.isNotEmpty())
                list.clear()
            list.addAll(respond.data.data)

        }
        adapter.notifyDataSetChanged()
    }

    override fun getCareNewsError(errorMessage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        filterItem(newText)
        return true
    }

    lateinit var adapter: AdapterCareNews
    lateinit var list: ArrayList<CareNewsEntity>
    lateinit var presenter: CareActivityPresenter
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care)
        initToolbar("CHĂM SÓC NUÔI DẠY CON", true)
        presenter = CareActivityPresenter(this)
        initView()
    }

    private fun initView() {
        list = ArrayList()
        adapter = AdapterCareNews(list, this, object : ItemClickListenerGeneric<CareNewsEntity> {
            override fun ItemClick(id: Int, data: CareNewsEntity?) {

            }

        })
        rcl_care_news.layoutManager = LinearLayoutManager(this)
        rcl_care_news.adapter = adapter
        presenter.getCareNews()
    }

    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<CareNewsEntity>()

        for (s in list) {
            if (TextUtils.unicodeToKoDauLowerCase(s.title.toLowerCase()).contains(TextUtils.unicodeToKoDauLowerCase(name.toLowerCase()))) {
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
