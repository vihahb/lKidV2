package com.sproject.ikidz.view.activity.learnProgram.viewpager

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.ContentEntity
import com.sproject.ikidz.model.entity.DataWeek
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.callback.ItemMoreActionGeneric
import com.sproject.ikidz.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_data_week.*
import java.io.Serializable
import java.util.*

class LearnProgramFragment : BaseFragment() {

    companion object {
        fun newInstance(data: DataWeek): LearnProgramFragment {
            var args = Bundle()
            args.putSerializable(Constants.OBJECT, data as Serializable)
            val fragment = LearnProgramFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var adapter: AdapterLearnProgram
    lateinit var list: ArrayList<ContentEntity>
    lateinit var data: DataWeek

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = arguments!!.getSerializable(Constants.OBJECT) as DataWeek

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_data_week, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        list = ArrayList()
//        adapter = AdapterLearnProgram(list, context, object : ItemMoreActionGeneric<ContentEntity> {
//            override fun ItemClick(type: Int, id: Int, data: ContentEntity) {
//
//            }
//        })
        list.addAll(data.data_week)
        rcl_data_week.layoutManager = LinearLayoutManager(context)
        rcl_data_week.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}