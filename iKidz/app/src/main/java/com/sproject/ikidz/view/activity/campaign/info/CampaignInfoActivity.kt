package com.sproject.ikidz.view.activity.campaign.info

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.CampainEntity
import com.sproject.ikidz.model.entity.InfoCampaignEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_campaign_info.*

class CampaignInfoActivity : BaseActivity(), ICampaignInfo {
    override fun getInfoCampaignError(errorMessage: String) {
        showLongToast(errorMessage)
        showView(false)
    }

    override fun getInfoCampaignSuccess(data: InfoCampaignEntity) {
        showView(true)
        if (!data.answer1.isEmpty()) {
            rd_1.text = data.answer1
        } else {
            rd_1.visibility = View.GONE
        }
        if (!data.answer2.isEmpty()) {
            rd_2.text = data.answer2
        } else {
            rd_2.visibility = View.GONE
        }
        if (!data.answer3.isEmpty()) {
            rd_3.text = data.answer3
        } else {
            rd_3.visibility = View.GONE
        }
        if (!data.answer4.isEmpty()) {
            rd_4.text = data.answer4
        } else {
            rd_4.visibility = View.GONE
        }
    }

    private fun showView(b: Boolean) {
        if (b) {
            ln_button.visibility = View.VISIBLE
            radio_campaign.visibility = View.VISIBLE
        } else {
            ln_button.visibility = View.INVISIBLE
            radio_campaign.visibility = View.INVISIBLE
        }
    }

    lateinit var entity: CampainEntity
    lateinit var presenter: CampaignInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_info)
        initToolbar("THĂM DÒ Ý KIẾN", true)
        presenter = CampaignInfoPresenter(this)
        getData()
    }

    private fun getData() {
        entity = intent.getSerializableExtra(Constants.OBJECT) as CampainEntity
        if (entity != null) {
            tv_title.text = entity.name
            tv_content.text = entity.question
            presenter.getInfoCampaign(entity.questionId.toInt())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
