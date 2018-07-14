package com.snq.teacher.view.activity.login

import android.os.Bundle
import android.view.MenuItem
import com.snq.teacher.R
import com.snq.teacher.presenter.Login.ResetPresenter
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_reset_password.*
import java.util.regex.Pattern

class ResetPasswordActivity : BaseActivity(), IResetView {

    lateinit var presenter: ResetPresenter
    var link_api = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        setContentView(R.layout.activity_reset_password)
        initToolbar("Quên mật khẩu", true)
        presenter = ResetPresenter(this)
        btnSend.setOnClickListener {
            if (validData(edtEmail.text.toString())) {
                presenter.sendResetRequest(link_api, edtEmail.text.toString())
            }
        }
        setTitleToolbar("Quên mật khẩu")
    }

    private fun validData(email: String): Boolean {
        if (email.isEmpty()) {
            showLongToast("Email không được để trống")
            return false
        }

        val regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(email)

        if (!matcher.matches()) {
            showLongToast("Email không đúng định dạng")
            return false
        }

        if (link_api.isEmpty()) {
            showLongToast("Vui lòng chọn trường ở màn đăng nhập")
            return false
        }
        return true
    }

    private fun getData() {
        link_api = intent.getStringExtra(Constants.LINK_API)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
