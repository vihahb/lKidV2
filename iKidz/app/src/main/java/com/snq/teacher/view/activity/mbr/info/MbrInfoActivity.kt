package com.snq.teacher.view.activity.mbr.info

import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import com.snq.teacher.R
import com.snq.teacher.model.entity.MbrInfoEntity
import com.snq.teacher.model.entity.StudentsEntity
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.TextUtils
import com.snq.teacher.sdk.Utils.WidgetUtils
import com.snq.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_mbr_info.*

class MbrInfoActivity : BaseActivity(), IMbrInfoActivity {
    override fun getMbrError(errorMessage: String) {
        showLongToast(errorMessage)
    }

    override fun getMbrInfoSuccess(data: MbrInfoEntity) {
        setView(data)
    }

    private fun setView(data: MbrInfoEntity) {
        if (!TextUtils.isEmpty(data.avatar)) {
            WidgetUtils.setImageURL(img_avatar, data.avatar, R.mipmap.ic_no_avatar)
        }

        if (!TextUtils.isEmpty(data.fullName)) {
            tv_name.text = data.fullName
        }

        if (!TextUtils.isEmpty(data.birthday)) {
            var content = "<b>Ngày sinh:</b> " + data.birthday
            tv_date.text = Html.fromHtml(content)
        } else {
            var content = "<b>Ngày sinh:</b> Chưa có"
            tv_date.text = Html.fromHtml(content)
        }

        if (!TextUtils.isEmpty(data.bloodGroup)) {
            var content = "<b>Nhóm máu:</b> " + data.bloodGroup
            tv_blood_group.text = Html.fromHtml(content)
        } else {
            var content = "<b>Nhóm máu:</b> Chưa có"
            tv_blood_group.text = Html.fromHtml(content)
        }
        if (!TextUtils.isEmpty(data.weight)) {
            var content = "<b>Cân nặng:</b> " + data.weight + " kg"
            tv_weight.text = Html.fromHtml(content)
        } else {
            var content = "<b>Cân nặng:</b> Chưa có"
            tv_weight.text = Html.fromHtml(content)
        }

        if (!TextUtils.isEmpty(data.height)) {
            var content = "<b>Chiều cao:</b> " + data.height + " cm"
            tv_height.text = Html.fromHtml(content)
        } else {
            var content = "<b>Chiều cao:</b> Chưa có"
            tv_height.text = Html.fromHtml(content)
        }
    }

    lateinit var presenter: MbrInfoPresenter
    lateinit var entity: StudentsEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mbr_info)
        initToolbar("SỔ SỨC KHỎE", true)
        presenter = MbrInfoPresenter(this)
        getData()
    }

    private fun getData() {
        entity = intent.getSerializableExtra(Constants.OBJECT) as StudentsEntity
        presenter.getMbrInfo(entity.id)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
