package com.sproject.ikidz.view.activity.sleepActivity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.SleepEntity
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.sdk.callback.DatePickerListener
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_sleep.*
import java.util.*

class SleepActivity : BaseActivity(), ISleepActivity, SearchView.OnQueryTextListener {
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

    override fun getSleepData(data: List<SleepEntity>) {
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

    override fun getSleepDataError(errorMessage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
    }

    lateinit var presenter: SleepActivityPresenter
    lateinit var list: ArrayList<SleepEntity>
    lateinit var adapter: AdapterSleep
    lateinit var date: String
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep)
        initToolbar("HOẠT ĐỘNG NGỦ", true)
        presenter = SleepActivityPresenter(this)
        initView()
    }

    private fun initView() {

        date = TimeUtils.getCurrentTimeFormat("dd/MM/yyyy")
        edt_time.setText(date)
        edt_time.setOnClickListener {
            TimeUtils.getInstance().showDatePickerDialog(this, object : DatePickerListener {
                override fun onSelected(year: Int, month: Int, day: Int) {
                    date = "" + day + "/" + (month + 1) + "/" + year;
                    edt_time.setText(date)
                    presenter.getDataSleep(date)
                }

            })
        }
        list = ArrayList()
        adapter = AdapterSleep(list, this, object : ItemClickListenerGeneric<SleepEntity> {
            override fun ItemClick(id: Int, data: SleepEntity?) {

            }
        })
        rcl_sleep.layoutManager = LinearLayoutManager(this)
        rcl_sleep.adapter = adapter
        presenter.getDataSleep(date)
    }

    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<SleepEntity>()

        for (s in list!!) {
            if (TextUtils.unicodeToKoDauLowerCase(s.fullName.toLowerCase()).contains(TextUtils.unicodeToKoDauLowerCase(name.toLowerCase()))) {
                filterdNames.add(s)
            }
        }
        adapter!!.filterList(filterdNames)
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
