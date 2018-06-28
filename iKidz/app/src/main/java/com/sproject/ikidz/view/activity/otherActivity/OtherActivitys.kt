package com.sproject.ikidz.view.activity.otherActivity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.OtherActivitysEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.sdk.callback.DatePickerListener
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.sdk.callback.TimePickerListener
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_other_activitys.*
import java.util.*

class OtherActivitys : BaseActivity(), IOtherActivitysView, SearchView.OnQueryTextListener {
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


    override fun getOthersList(data: List<OtherActivitysEntity>) {
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

    override fun getOthersListError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    lateinit var adapter: AdapterOtherActivitys
    lateinit var presenter: OthersActivityPresenter
    lateinit var list: ArrayList<OtherActivitysEntity>

    lateinit var end_time: String
    lateinit var start_time: String
    lateinit var date: String
    private var searchView: SearchView? = null
    lateinit var calendar: Calendar
    var content_time = ""
    lateinit var className: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_activitys)
        initToolbar("HOẠT ĐỘNG KHÁC", true)
        presenter = OthersActivityPresenter(this)
        initView()
    }

    private fun initView() {
        className = SharedUtils.getInstance().getStringValue(Constants.CURRENT_CLASS_TEACHER_NAME)
        if (!TextUtils.isEmpty(className))
            tv_class_name.text = className

        calendar = Calendar.getInstance()
        start_time = "" + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE)
        end_time = "" + (calendar.get(Calendar.HOUR) + 1) + ":" + calendar.get(Calendar.MINUTE)

        content_time = "<font color=\'#35cd1c\'>Bắt đầu: $start_time - </font><font color=\'#f31141\'>Kết thúc: $end_time"
        tv_time.text = Html.fromHtml(content_time)

        tv_time.setOnClickListener {
            selectTime()
        }
        img_time.setOnClickListener {
            selectTime()
        }

        date = TimeUtils.getCurrentTimeFormat("dd/MM/yyyy")
        edt_time.setText(date)
        edt_time.setOnClickListener {
            TimeUtils.getInstance().showDatePickerDialog(this, object : DatePickerListener {
                override fun onSelected(year: Int, month: Int, day: Int) {
                    date = "" + day + "/" + (month + 1) + "/" + year;
                    edt_time.setText(date)
                    presenter.getOthersList(date, start_time, end_time)
                }

            })
        }
        list = ArrayList()
        adapter = AdapterOtherActivitys(list, this, object : ItemClickListenerGeneric<OtherActivitysEntity> {
            override fun ItemClick(id: Int, data: OtherActivitysEntity) {

            }
        })
        rcl_other_activitys.layoutManager = LinearLayoutManager(this)
        rcl_other_activitys.adapter = adapter
        presenter.getOthersList(date, start_time, end_time)
    }

    fun selectTime() {
        TimeUtils.getInstance().showTimePickerDialog(this, object : TimePickerListener {
            override fun onTimeSelected(time: String, dateTime: String?) {
                start_time = time
                TimeUtils.getInstance().showTimePickerDialog(this@OtherActivitys, object : TimePickerListener {
                    override fun onTimeSelected(time: String, dateTime: String?) {
                        end_time = time
                        content_time = "<font color=\'#35cd1c\'>Bắt đầu: $start_time - </font><font color=\'#f31141\'>Kết thúc: $end_time"
                        tv_time.text = Html.fromHtml(content_time)
                        presenter.getOthersList(date, start_time, end_time)
                    }
                })
            }
        })
    }

    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<OtherActivitysEntity>()

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
