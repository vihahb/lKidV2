package com.sproject.ikidz.view.activity.subscribeToEat.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.EatTicketEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.view.base.BaseFragment
import com.twinkle94.monthyearpicker.picker.YearMonthPickerDialog
import kotlinx.android.synthetic.main.fragment_subscribe_to_eat.*
import java.util.*

class SubscribeToEatFragment : BaseFragment(), ISubscribeToEat {
    override fun getDataTicketSuccess(detail_ticket: List<EatTicketEntity>) {
        if (detail_ticket.isEmpty()) {
            tv_message.visibility = View.VISIBLE
            tv_message.text = context!!.resources.getString(R.string.mesage_no_data)
        } else {
            tv_message.visibility = View.GONE
            list.clear()
            list.addAll(detail_ticket)
            adapter.notifyDataSetChanged()
        }
    }

    override fun getDataTicketError(errorMessage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
    }

    companion object {
        fun newInstance(type: Int): SubscribeToEatFragment {
            val args = Bundle()
            args.putInt(Constants.EAT_TYPE, type)
            val fragment = SubscribeToEatFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var type = -1
    lateinit var presenter: SubscribeToEatPresenter
    lateinit var list: ArrayList<EatTicketEntity>
    lateinit var calendar: Calendar
    lateinit var adapter: AdapterSubcribeEat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(Constants.EAT_TYPE)
        presenter = SubscribeToEatPresenter(this)
        list = ArrayList()
        calendar = Calendar.getInstance()
        adapter = AdapterSubcribeEat(list, context, object : ItemClickListenerGeneric<EatTicketEntity> {
            override fun ItemClick(id: Int, data: EatTicketEntity?) {

            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_subscribe_to_eat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (type) {
            1 -> {
                ln_select_month.visibility = View.GONE
                presenter.getSubscribeTicket()
            }
            2 -> {
                ln_select_month.visibility = View.VISIBLE
                presenter.getLogSubscribeTicket(getCurrentMonth(), getCurrentYear())
            }
        }
        initView()
    }

    private fun initView() {
        rcl_subcribe_to_eat.layoutManager = LinearLayoutManager(context)
        rcl_subcribe_to_eat.adapter = adapter
        edt_time.setText("" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR))
        edt_time.setOnClickListener {

            var picker = YearMonthPickerDialog(context, YearMonthPickerDialog.OnDateSetListener { year, month ->
                edt_time.setText("" + (month + 1) + "/" + year)
                presenter.getLogSubscribeTicket(month + 1, year)
            })
            picker.show()
        }
    }

    fun getCurrentMonth(): Int {
        return calendar.get(Calendar.MONTH) + 1
    }

    fun getCurrentYear(): Int {
        return calendar.get(Calendar.YEAR)
    }
}