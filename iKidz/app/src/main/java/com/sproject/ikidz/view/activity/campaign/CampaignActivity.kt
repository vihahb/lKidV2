package com.sproject.ikidz.view.activity.campaign

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.CampainEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.view.activity.campaign.info.CampaignInfoActivity
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_campaign.*
import java.util.*

class CampaignActivity : BaseActivity(), ICampaignView {
    override fun getcampaignListSuccess(data: List<CampainEntity>) {
        if (data.isNotEmpty()) {
            list.addAll(data)
            adapterCampaign.notifyDataSetChanged()
        } else {
            tv_message.visibility = View.VISIBLE
            tv_message.text = resources.getString(R.string.mesage_no_data)
        }
    }

    override fun getcampaignListEror(mesage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = resources.getString(R.string.mesage_no_data)
    }

    lateinit var list: ArrayList<CampainEntity>
    lateinit var presenter: CampaignPresenter
    lateinit var adapterCampaign: AdapterCampaign

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign)
        initToolbar("THĂM DÒ Ý KIẾN", true)
        initView()
    }

    private fun initView() {
        list = ArrayList()
        presenter = CampaignPresenter(this)
        adapterCampaign = AdapterCampaign(list, this@CampaignActivity, ItemClickListenerGeneric<CampainEntity> { id, data ->
            startActivity(CampaignInfoActivity::class.java, Constants.OBJECT, data)
        })
        rcl_campaign.layoutManager = LinearLayoutManager(this@CampaignActivity)
        rcl_campaign.adapter = adapterCampaign
        presenter.getCampaignSurvey()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
