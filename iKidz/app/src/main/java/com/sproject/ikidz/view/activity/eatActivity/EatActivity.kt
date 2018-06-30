package com.sproject.ikidz.view.activity.eatActivity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.EatEntity
import com.sproject.ikidz.model.entity.MenuEatEntity
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.sdk.callback.DatePickerListener
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_eat.*
import java.util.*

class EatActivity : BaseActivity(), IEatActivity {
    override fun getMenuEatSuccess(menuList: List<MenuEatEntity>) {
        this.menuList.clear()
        this.menuList.addAll(menuList)
        adapterSpinnerMenu.notifyDataSetChanged()
        presenter.getEatActivity(date, menuList[0].code)
        initMenuList(menuList)
    }

    private fun initMenuList(menuList: List<MenuEatEntity>) {
        if (menuList.isNotEmpty())
            for (i in menuList) {
                when (i.code) {
                    "breakfast" -> {
                        if (!i.content.isNullOrEmpty()) {
                            ln_breakfast.visibility = View.VISIBLE
                            tv_breakfast.text = i.name + ": " + i.content
                        } else {
                            ln_breakfast.visibility = View.GONE
                        }
                    }
                    "snack_time_morning" -> {
                        if (!i.content.isNullOrEmpty()) {
                            ln_snack_time_morning.visibility = View.VISIBLE
                            tv_snack_time_morning.text = i.name + ": " + i.content
                        } else {
                            tv_snack_time_morning.visibility = View.GONE
                        }
                    }
                    "lunch" -> {
                        if (!i.content.isNullOrEmpty()) {
                            ln_lunch.visibility = View.VISIBLE
                            tv_lunch.text = i.name + ": " + i.content
                        } else {
                            tv_lunch.visibility = View.GONE
                        }
                    }
                    "snack_time_dinner" -> {
                        if (!i.content.isNullOrEmpty()) {
                            ln_snack_time_dinner.visibility = View.VISIBLE
                            tv_snack_time_dinner.text = i.name + ": " + i.content
                        } else {
                            tv_snack_time_dinner.visibility = View.GONE
                        }
                    }
                    "dinner" -> {
                        if (!i.content.isNullOrEmpty()) {
                            ln_dinner.visibility = View.VISIBLE
                            tv_dinner.text = i.name + ": " + i.content
                        } else {
                            ln_dinner.visibility = View.GONE
                        }
                    }
                }

            }
    }

    override fun getEatActivitySuccess(eatList: List<EatEntity>) {
        this.eatList.clear()
        this.eatList.addAll(eatList)
        adapterEatActivity.notifyDataSetChanged()
    }

    override fun getMenuEatError(errorMessage: String?) {
        showLongToast(errorMessage)
    }

    override fun getEatActivityError(errorMessage: String?) {
        showLongToast(errorMessage)
    }

    lateinit var presenter: EatActivityPresenter
    lateinit var menuList: ArrayList<MenuEatEntity>
    lateinit var eatList: ArrayList<EatEntity>
    lateinit var adapterSpinnerMenu: AdapterSpinnerMenu
    lateinit var adapterEatActivity: AdapterEatActivity
    lateinit var date: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eat)
        initToolbar("HOẠT ĐỘNG ĂN", true)
        presenter = EatActivityPresenter(this)
        initView()
    }

    private fun initView() {
        date = TimeUtils.getCurrentTimeFormat("dd-MM-yyyy")
        edt_time.setText(date)
        menuList = ArrayList()
        eatList = ArrayList()
        edt_time.setOnClickListener {
            TimeUtils.getInstance().showDatePickerDialog(this, object : DatePickerListener {
                override fun onSelected(year: Int, month: Int, day: Int) {
                    date = "" + day + "-" + (month + 1) + "-" + year
                    edt_time.setText(date)
                    presenter.getEatMenu(date)
//                    presenter.getEatActivity(date, menuList[sp_menu.selectedItemPosition].code)
                }
            })
        }

        sp_menu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getEatActivity(date, menuList[position].code)
            }
        }

        adapterEatActivity = AdapterEatActivity(eatList, this, object : ItemClickListenerGeneric<EatEntity> {
            override fun ItemClick(id: Int, data: EatEntity?) {

            }
        })

        adapterSpinnerMenu = AdapterSpinnerMenu(this, menuList)

        sp_menu.adapter = adapterSpinnerMenu

        rcl_eat_activity.layoutManager = LinearLayoutManager(this)
        rcl_eat_activity.adapter = adapterEatActivity

        presenter.getEatMenu(date)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
