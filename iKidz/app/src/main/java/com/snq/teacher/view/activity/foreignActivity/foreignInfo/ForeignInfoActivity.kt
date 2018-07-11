package com.snq.teacher.view.activity.foreignActivity.foreignInfo

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.snq.teacher.R
import com.snq.teacher.iKidApplications
import com.snq.teacher.model.entity.DataForeignInfo
import com.snq.teacher.model.entity.ForeignActivityEntity
import com.snq.teacher.model.entity.RegisterEntity
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.TextUtils
import com.snq.teacher.view.base.BaseActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_foreign_info.*
import java.util.*

class ForeignInfoActivity : BaseActivity(), IGetForeignInfo {

    var TAG = "ForeignInfoActivity"

    override fun getForeignInfoSuccess(info: DataForeignInfo) {

        if (!TextUtils.isEmpty(info.name))
            tv_title.text = info.name

        if (!TextUtils.isEmpty(info.content))
            webview.loadData(info.content, "text/html", "UTF-8")

        if (!TextUtils.isEmpty(info.fee))
            feeContent = "Chi phí: <font color=\'#ff7300\'>" + info.fee + "</font>"

        if (!TextUtils.isEmpty(info.date))
            feeContent += " - Hạn đăng ký: <font color=\'#ff7300\'>" + info.date + "</font>"

        tv_fee.text = Html.fromHtml(feeContent)

        if (info.registed.size > 0) {
            listRegister.addAll(info.registed)
            adapterRegister.notifyDataSetChanged()
        }

        if (!TextUtils.isEmpty(info.image))
            Picasso.with(iKidApplications.context)
                    .load(info.image)
                    .noPlaceholder()
                    .error(R.drawable.ic_load_image_error)
                    .into(img_image, object : Callback {
                        override fun onSuccess() {
                            iKidApplications.log(TAG, "Load image success url: " + info.image.toString())
                        }

                        override fun onError() {
                            iKidApplications.log(TAG, "Load image error url: " + info.image.toString())
                        }
                    })

    }

    override fun getForeignError(s: String) {
        closeProgressBar()
        tv_message.visibility = View.VISIBLE
        tv_message.text = s
    }

    var title = ""
    var feeContent = ""
    private lateinit var entity: ForeignActivityEntity
    private lateinit var presenter: ForeignInfoActivityPresenter
    private lateinit var listRegister: ArrayList<RegisterEntity>
    private lateinit var adapterRegister: AdapterRegister


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreign_info)
        initToolbar("HOẠT ĐỘNG NGOẠI KHÓA", true)
        presenter = ForeignInfoActivityPresenter(this)
        initView()
        getData()

    }

    private fun initView() {
        listRegister = ArrayList()
        adapterRegister = AdapterRegister(listRegister, this@ForeignInfoActivity)
        rcl_register.layoutManager = LinearLayoutManager(this@ForeignInfoActivity)
        rcl_register.adapter = adapterRegister

        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(viewx: WebView, urlx: String): Boolean {
                viewx.loadUrl(urlx)
                return true
            }
        }
    }

    private fun getData() {
        entity = intent.getSerializableExtra(Constants.OBJECT) as ForeignActivityEntity
        if (entity != null) {
            presenter.getForeignInfo(entity.id)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
