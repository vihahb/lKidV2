package com.sproject.ikidz.view.activity.editProfile

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.DataUser
import com.sproject.ikidz.presenter.editProfile.EditProfilePresenter
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.sdk.Utils.WidgetUtils
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.util.*

class EditProfileActivity : BaseActivity(), iEditProfile {
    var edit = false

    override fun updateProfileSuccess() {

    }

    private lateinit var genders: ArrayList<String>
    lateinit var adapter: AdapterGender

    override fun getProfileSuccess(user: DataUser) {
        if (!user.user.avatar.isEmpty()) {
            WidgetUtils.setImageURL(img_user_ava, user.user.avatar, R.mipmap.ic_launcher)
            WidgetUtils.setImageURL(img_user_ava_bg, user.user.avatar, R.color.white_100)
        }

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

        if (SharedUtils.getInstance().getBooleanValue(Constants.TEACHER)) {
            sp_gender.setSelection(user.user.teachers.gender)
        } else {
            sp_gender.setSelection(user.user.parents.gender)
        }

    }

    lateinit var presenter: EditProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        initToolbar(getString(R.string.title_edit_profile), true)
        initView()
        presenter = EditProfilePresenter(this)
    }

    private fun initView() {
        genders = ArrayList<String>()
        genders.add(0, "Nữ")
        genders.add(1, "Nam")
        adapter = AdapterGender(genders, this)
        sp_gender.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.menu_edit -> {
                if (!edit) {
                    edit = true
                    item.setIcon(R.drawable.ic_action_clear)
                    editField(true)
                } else {
                    edit = false
                    item.setIcon(R.drawable.ic_action_edit)
                    editField(false)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editField(edit: Boolean) {
        edt_phone.isEnabled = edit
        edt_email.isEnabled = edit
        edt_full_name.isEnabled = edit
        sp_gender.isEnabled = edit

        if (edit) {
            btnUpdateAcc.visibility = View.VISIBLE
            btnChangePicture.visibility = View.VISIBLE
        } else {
            btnUpdateAcc.visibility = View.INVISIBLE
            btnChangePicture.visibility = View.INVISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
