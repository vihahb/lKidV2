package com.sproject.ikidz.view.activity.editProfile

import android.os.Bundle
import android.view.MenuItem
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.DataUser
import com.sproject.ikidz.presenter.editProfile.EditProfilePresenter
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.sdk.Utils.WidgetUtils
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : BaseActivity(), iEditProfile {
    override fun updateProfileSuccess() {

    }

    override fun getProfileSuccess(user: DataUser) {
        if (!user.user.avatar.isEmpty())
            WidgetUtils.setImageURL(img_user_ava, user.user.avatar, R.mipmap.ic_launcher)

        if (!user.user.fullName.isEmpty())
            edt_full_name.setText(user.user.fullName)
        if (!user.user.email.isEmpty())
            edt_email.setText(user.user.email)
        if (SharedUtils.getInstance().getBooleanValue(Constants.TEACHER)) {
            if (!user.user.teachers.phone.isEmpty())
                edt_phone.setText(user.user.teachers.phone)
            else
                edt_phone.hint = "Chưa có số điện thoại"
        } else {
            if (!user.user.teachers.phone.isEmpty())
                edt_phone.setText(user.user.parents.phone)
            else
                edt_phone.hint = "Chưa có số điện thoại"
        }
    }

    lateinit var presenter: EditProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        initToolbar(R.id.toolbar, getString(R.string.title_edit_profile), true)
        presenter = EditProfilePresenter(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
