package com.snq.teacher.view.activity.school

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.snq.teacher.R
import com.snq.teacher.model.entity.NewsEntity
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.TextUtils
import com.snq.teacher.sdk.callback.ItemClickListenerGeneric
import com.snq.teacher.view.activity.school.info.SchoolsNewsInfo
import com.snq.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_school.*
import java.io.Serializable

class SchoolActivity : BaseActivity(), ISchoolsList, SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        filterItem(newText)
        return true
    }

    override fun getSchoolsSuccess(data: List<NewsEntity>, page: Int) {
        tv_message.visibility = View.GONE
        if (page == 0)
            list.clear()

        list.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun getSchoolsError(errorMessage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
    }

    var page = 1
    lateinit var presenter: SchoolsPresenter
    lateinit var adapter: AdapterSchoolsNews
    lateinit var list: java.util.ArrayList<NewsEntity>
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)
        initToolbar("NHÀ TRƯỜNG", true)
        initView()
    }

    fun initView() {
        presenter = SchoolsPresenter(this)
        list = ArrayList()
        rcl_schools.layoutManager = LinearLayoutManager(this)
        adapter = AdapterSchoolsNews(list, this@SchoolActivity, ItemClickListenerGeneric<NewsEntity> { _, data ->
            var intent = Intent(this@SchoolActivity, SchoolsNewsInfo::class.java)
            intent.putExtra(Constants.OBJECT, data as Serializable)
            startActivity(intent)
        })
        rcl_schools.adapter = adapter
        presenter.getSchool(page)
    }

    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<NewsEntity>()

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
