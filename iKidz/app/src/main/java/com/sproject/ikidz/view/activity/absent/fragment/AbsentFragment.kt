package com.sproject.ikidz.view.activity.absent.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.AbsentEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.callback.ItemClickListener
import com.sproject.ikidz.view.activity.absent.presenter.AbsentFragmentPre
import com.sproject.ikidz.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_absent.*
import java.util.*

class AbsentFragment : BaseFragment(), IAbsentFragment {
    override fun absentError(errorMessage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
    }

    override fun getAbsentSuccess(data: List<AbsentEntity>) {
        list.addAll(data)
        adapter.notifyDataSetChanged()
    }

    var type = -1
    var page = 1

    lateinit var presenter: AbsentFragmentPre
    lateinit var adapter: AdapterAbsentFragment
    lateinit var list: ArrayList<AbsentEntity>

    companion object {
        fun newInstance(type: Int): AbsentFragment {
            val args = Bundle()
            args.putInt(Constants.ABSENT_TYPE, type)
            val fragment = AbsentFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(Constants.ABSENT_TYPE)

        presenter = AbsentFragmentPre(this)

        list = ArrayList()
        adapter = AdapterAbsentFragment(list, context, object : ItemClickListener {
            override fun ItemClick(id: Int) {

            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_absent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        rcl_absent.layoutManager = LinearLayoutManager(context)
        rcl_absent.adapter = adapter
        presenter.getAbsentByClass(page, type)
    }
}