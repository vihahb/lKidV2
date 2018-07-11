package com.snq.ikidz.teacher.view.activity.campaign.info

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.RESP.RESP_DataAnswer
import com.snq.ikidz.teacher.model.entity.CampainEntity
import com.snq.ikidz.teacher.model.entity.InfoCampaignEntity
import com.snq.ikidz.teacher.model.entity.RespondResultCampaign
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_campaign_info.*
import java.util.*

class CampaignInfoActivity : BaseActivity(), ICampaignInfo {
    override fun getResultError(errorMessage: String) {
        showLongToast(errorMessage)
    }

    override fun getResultSuccess(data: RespondResultCampaign) {
        radio_campaign.visibility = View.INVISIBLE
        ln_button.visibility = View.INVISIBLE
        showChar(data)
    }

    private fun showChar(data: RespondResultCampaign) {
        data.data.forEachIndexed { index, element ->
            listDataSet.add(PieEntry(element.count.toFloat(), element.value))
            listDataSetLable.add(element.value)

        }

        var dataSet = PieDataSet(listDataSet, "")
        dataSet.sliceSpace = 2f
        dataSet.valueTextSize = 12f

        val colors = ArrayList<Int>()
        colors.add(Color.CYAN)
        colors.add(Color.GREEN)
        colors.add(Color.YELLOW)
        colors.add(Color.GRAY)
        colors.add(Color.BLUE)
        colors.add(Color.RED)
        colors.add(Color.MAGENTA)
        dataSet.colors = colors


        //add legend to chart
        val legend = resultChar.legend
        legend.form = Legend.LegendForm.CIRCLE
        legend.position = Legend.LegendPosition.LEFT_OF_CHART

        var pieData = PieData(dataSet)
        resultChar.data = pieData
        resultChar.isDrawHoleEnabled = false
        resultChar.visibility = View.VISIBLE
        resultChar.invalidate()
    }

    override fun sendQuestionSuccess(data: RESP_DataAnswer) {
        showLongToast("Gửi kết quả thành công!")
        disabeView(false)
    }

    private fun disabeView(b: Boolean) {
        btnVote.isEnabled = b
        rd_1.isEnabled = b
        rd_2.isEnabled = b
        rd_3.isEnabled = b
        rd_4.isEnabled = b
    }

    override fun sendQuestionError(s: String) {
        showLongToast(s)
    }

    override fun getInfoCampaignError(errorMessage: String) {
        showLongToast(errorMessage)
        showView(false)
    }

    override fun getInfoCampaignSuccess(data: InfoCampaignEntity) {
        respond = data

        showView(true)
        if (data.answer1 != null && !data.answer1.isEmpty()) {
            rd_1.text = data.answer1
        } else {
            rd_1.visibility = View.GONE
        }
        if (data.answer2 != null && !data.answer2.isEmpty()) {
            rd_2.text = data.answer2
        } else {
            rd_2.visibility = View.GONE
        }
        if (data.answer3 != null && !data.answer3.isEmpty()) {
            rd_3.text = data.answer3
        } else {
            rd_3.visibility = View.GONE
        }
        if (data.answer4 != null && !data.answer4.isEmpty()) {
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

    lateinit var listDataSet: ArrayList<PieEntry>
    lateinit var listDataSetLable: ArrayList<String>
    lateinit var entity: CampainEntity
    lateinit var presenter: CampaignInfoPresenter
    var respond: InfoCampaignEntity? = null

    var value = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_info)
        initToolbar("THĂM DÒ Ý KIẾN", true)
        presenter = CampaignInfoPresenter(this)
        getData()
        initView()
    }

    private fun initView() {

        resultChar.isRotationEnabled = false
        resultChar.holeRadius = 5f
        resultChar.setTransparentCircleAlpha(0)
        resultChar.setCenterTextSize(10F)

        listDataSet = ArrayList()
        listDataSetLable = ArrayList()
        radio_campaign.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rd_1 -> {
                    value = rd_1.text.toString()
                }
                R.id.rd_2 -> {
                    value = rd_2.text.toString()
                }
                R.id.rd_3 -> {
                    value = rd_3.text.toString()
                }
                R.id.rd_4 -> {
                    value = rd_4.text.toString()
                }
            }
        }

        btnVote.setOnClickListener {
            if (value.isEmpty()) {
                showLongToast("Vui lòng chọn kết quả!")
            } else {
                presenter.sendQuestion(value, respond!!.pollId.toInt(), entity.questionId.toInt())
            }
        }

        btnResult.setOnClickListener {
            presenter.getResult(entity.questionId.toInt())
        }
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
