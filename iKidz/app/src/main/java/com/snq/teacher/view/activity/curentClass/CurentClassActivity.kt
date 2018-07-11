package com.snq.teacher.view.activity.curentClass

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.snq.teacher.R
import com.snq.teacher.model.entity.StudentEntity
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.TextUtils
import com.snq.teacher.sdk.callback.ItemClickListener
import com.snq.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_curent_class.*
import java.util.*

class CurentClassActivity : BaseActivity(), ICurrentClass, SearchView.OnQueryTextListener {
    override fun getCurrentClassSuccess(data: List<StudentEntity>) {
        if (data.isEmpty()) {
            tv_message.visibility = View.VISIBLE
            tv_message.text = activity!!.resources.getString(R.string.mesage_no_data)
        }

        list.addAll(data)
        adapter.notifyDataSetChanged()

    }

    override fun getClassError(errorMessage: String?) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
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

    var title = ""
    private var searchView: SearchView? = null
    lateinit var list: ArrayList<StudentEntity>
    private lateinit var adapter: AdapterCurrentClass
    private lateinit var presenter: CurrentClassPre
    var page = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curent_class)
        getData()
        initView()
    }

    private fun initView() {
        presenter = CurrentClassPre(this)
        list = ArrayList()
        adapter = AdapterCurrentClass(list, this, object : ItemClickListener {
            override fun ItemClick(id: Int) {

            }
        })
        rcl_current_class.layoutManager = LinearLayoutManager(this)
        rcl_current_class.adapter = adapter
        presenter.getCurrentClass(page)
    }

    private fun getData() {
        title = intent.getStringExtra(Constants.TITLE)
        if (!title.isEmpty()) {
            initToolbar(title.toUpperCase(), true)
        }
    }

    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<StudentEntity>()

        for (s in list!!) {
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
