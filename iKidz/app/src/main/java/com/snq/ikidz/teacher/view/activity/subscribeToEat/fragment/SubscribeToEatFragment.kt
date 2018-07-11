package com.snq.ikidz.teacher.view.activity.subscribeToEat.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rackspira.kristiawan.rackmonthpicker.RackMonthPicker
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.entity.EatTicketEntity
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.callback.ItemClickListenerGeneric
import com.snq.ikidz.teacher.view.base.BaseFragment
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

            RackMonthPicker(context)
                    .setLocale(Locale.ENGLISH)
                    .setPositiveButton { month, startDate, endDate, year, monthLabel ->
                        edt_time.setText("" + month + "/" + year)
                        presenter.getLogSubscribeTicket(month + 1, year)
                    }
                    .setNegativeButton {
                        it.dismiss()
                    }.show()
        }
    }

    fun getCurrentMonth(): Int {
        return calendar.get(Calendar.MONTH) + 1
    }

    fun getCurrentYear(): Int {
        return calendar.get(Calendar.YEAR)
    }
}