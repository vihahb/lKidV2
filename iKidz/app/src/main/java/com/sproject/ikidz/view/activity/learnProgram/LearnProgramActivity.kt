package com.sproject.ikidz.view.activity.learnProgram

import android.os.Bundle
import android.view.MenuItem
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.DataWeek
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.sdk.callback.DatePickerListener
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_learn_program.*
import java.util.*

class LearnProgramActivity : BaseActivity(), ILearnProgram {
    override fun getLearnProgramSuccess(data: List<DataWeek>) {
        if (data.isNotEmpty())
            initViewPager(data)
    }

    override fun getLearnProgramerror(errorMessage: String) {
    }

    lateinit var list: ArrayList<DataWeek>
    lateinit var pre: LearnProgramPre
    lateinit var adapterVp: ViewPageLearnProgram
    lateinit var start_date: String
    lateinit var end_date: String
    lateinit var class_name: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_program)
        initToolbar("CHƯƠNG TRÌNH HỌC", true)
        pre = LearnProgramPre(this)
        getData()
        initView()
    }

    private fun getData() {
        var calendar = Calendar.getInstance()
        tv_year_name.text = "Năm học: " + calendar[Calendar.YEAR] + " - " + (calendar[Calendar.YEAR] + 1)

        class_name = SharedUtils.getInstance().getStringValue(Constants.CURRENT_CLASS_TEACHER_NAME)
        if (!class_name.isEmpty())
            tv_class_name.text = class_name
    }

    private fun initView() {
        list = ArrayList()
        start_date = TimeUtils.getFirstDayMonthFormat("dd-MM-yyyy")
        end_date = TimeUtils.getLastDayMonthFormat("dd-MM-yyyy")

        edt_time_start.setText(start_date)
        edt_time_end.setText(end_date)

        edt_time_start.setOnClickListener {
            TimeUtils.getInstance().showDatePickerDialog(this) { year, month, day ->
                start_date = "" + day + "-" + (month + 1) + "-" + year
                edt_time_start.setText(start_date)

                TimeUtils.getInstance().showDatePickerDialog(this@LearnProgramActivity, object : DatePickerListener {
                    override fun onSelected(year: Int, month: Int, day: Int) {
                        end_date = "" + day + "-" + (month + 1) + "-" + year
                        edt_time_end.setText(end_date)
                        pre.getLEarnProgram(start_date, end_date)
                    }
                })
            }
        }

        edt_time_end.setOnClickListener {
            TimeUtils.getInstance().showDatePickerDialog(this) { year, month, day ->
                end_date = "" + day + "-" + (month + 1) + "-" + year
                edt_time_end.setText(start_date)
                pre.getLEarnProgram(start_date, end_date)
            }
        }

        pre.getLEarnProgram(start_date, end_date)
    }

    fun initViewPager(list: List<DataWeek>) {
        this.list.addAll(list)
        adapterVp = ViewPageLearnProgram(supportFragmentManager, this@LearnProgramActivity, this.list)
        vpProgram.adapter = adapterVp
        tablayoutProgram.setupWithViewPager(vpProgram)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
