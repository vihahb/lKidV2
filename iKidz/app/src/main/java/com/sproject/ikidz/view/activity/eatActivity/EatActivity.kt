package com.sproject.ikidz.view.activity.eatActivity

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.DataEatEntity
import com.sproject.ikidz.model.entity.EatEntity
import com.sproject.ikidz.model.entity.EatValue
import com.sproject.ikidz.model.entity.MenuEatEntity
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.sdk.callback.DatePickerListener
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_eat.*
import java.util.*

class EatActivity : BaseActivity(), IEatActivity {
    override fun updateEatValueSuccess(position: Int, data: EatValue) {
        showLongToast("Cập nhật thông tin thành công")
        eatList[position].note = data.note
        eatList[position].eatStatus = data.eatStatus
        eatList[position].activityName = data.activityName
        adapterEatActivity.notifyDataSetChanged()
        dialog.dismiss()
    }

    override fun updateEatValueError() {
        dialog.dismiss()
    }

    override fun getMenuEatSuccess(menuList: List<MenuEatEntity>) {
        this.menuList.clear()
        this.menuList.addAll(menuList)
        adapterSpinnerMenu.notifyDataSetChanged()
        if (menuList.isNotEmpty()) {
            presenter.getEatActivity(date, menuList[0].code)
            initMenuList(menuList)
        }
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

    override fun getEatActivitySuccess(eatList: DataEatEntity) {
        this.eatList.clear()
        this.eatList.addAll(eatList.activity)
        adapterEatActivity.notifyDataSetChanged()

        if (!spStateList.isEmpty())
            spStateList.clear()
        spStateList.addAll(eatList.status_eat)
        adapterSpinnerEatState.notifyDataSetChanged()
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
    lateinit var spStateList: ArrayList<String>
    lateinit var date: String

    lateinit var spStateEat: Spinner
    lateinit var adapterSpinnerEatState: AdapterSpinnerEatState
    lateinit var edtNote: EditText
    lateinit var tv_name: TextView
    lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eat)
        initToolbar("HOẠT ĐỘNG ĂN", true)
        presenter = EatActivityPresenter(this)
        initView()
    }

    private fun initView() {
        initDialog()
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
                showDialog(data!!, id)
            }
        })

        adapterSpinnerMenu = AdapterSpinnerMenu(this, menuList)

        sp_menu.adapter = adapterSpinnerMenu

        rcl_eat_activity.layoutManager = LinearLayoutManager(this)
        rcl_eat_activity.adapter = adapterEatActivity

        presenter.getEatMenu(date)
    }

    private fun showDialog(data: EatEntity, position: Int) {
        tv_name.text = data.fullName
        edtNote.setText(data.note)

        when (data.eatStatus) {
            "Con ăn hết" -> {
                spStateEat.setSelection(0)
            }
            "Con không chịu ăn" -> {
                spStateEat.setSelection(1)
            }
            "Con ăn không hết bát" -> {
                spStateEat.setSelection(2)
            }
            "Con không ăn" -> {
                spStateEat.setSelection(3)
            }
        }
        btnUpdate.setOnClickListener {
            var eatValue = EatValue(
                    data.studentId,
                    data.fullName,
                    edtNote.text.toString(),
                    spStateList[spStateEat.selectedItemPosition],
                    menuList[sp_menu.selectedItemPosition].code)
            presenter.updateEat(edt_time.text.toString(), eatValue, position)
        }
        dialog.show()
    }

    private lateinit var dialog: Dialog

    @SuppressLint("NewApi")
    private fun initDialog() {
        spStateList = ArrayList()
        dialog = Dialog(this, R.style.Theme_Transparent)
        dialog.setContentView(R.layout.dialog_eat)
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        tv_name = dialog.findViewById(R.id.tv_name)
        edtNote = dialog.findViewById(R.id.tv_eat_note)
        spStateEat = dialog.findViewById(R.id.sp_state_eat)
        val btnClose = dialog.findViewById<Button>(R.id.btnClose)
        btnUpdate = dialog.findViewById<Button>(R.id.btnUpdate)

        adapterSpinnerEatState = AdapterSpinnerEatState(this, spStateList)
        spStateEat.adapter = adapterSpinnerEatState

        btnClose.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
