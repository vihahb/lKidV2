package com.sproject.ikidz.presenter

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_CountNotify
import com.sproject.ikidz.model.RESP.RESP_CurrentClassTeacher
import com.sproject.ikidz.model.database.GetObjectByKeyModel
import com.sproject.ikidz.model.database.SaveObjectModel
import com.sproject.ikidz.model.entity.ClassCurrentTeacher
import com.sproject.ikidz.model.entity.DataUser
import com.sproject.ikidz.model.entity.ItemDrawer
import com.sproject.ikidz.model.server.GetClassCurrentOfTeacher
import com.sproject.ikidz.model.server.GetCountNotify
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.JsonHelper
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.view.activity.home.IHomeView
import java.util.*

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 10:57 PM
 * Email: vihahb@gmail.com
 */
class HomePresenter(private val view: IHomeView) {

    var TAG = "HomePresenter"
    init {
        if (SharedUtils.getInstance().getBooleanValue(Constants.TEACHER)){
            getCurrentClassTeacher()
        }
    }

    fun getCountNotify(class_id: Int) {
        if (!NetworkUtils.isConnected(view.activity)){
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        if (token != null && link_api != null){
            object : GetCountNotify(token, link_api, class_id, 1) {
                override fun onSuccess(notify: RESP_CountNotify?) {
                    iKidApplications.log(TAG, JsonHelper.toJson(notify!!))
                    view.getSetNotify(notify.data.data)
                }

                override fun onError(s: String?) {
                    iKidApplications.log(TAG, s!!)
                }

            }
        }
    }

    private fun getCurrentClassTeacher(){
        if (!NetworkUtils.isConnected(view.activity)){
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        if (token != null && link_api != null)
            object : GetClassCurrentOfTeacher(token, link_api) {
                override fun onSuccess(data: RESP_CurrentClassTeacher?) {
                    iKidApplications.log(TAG, JsonHelper.toJson(data!!))
                    getCountNotify(data.data.id)
                    view.getCurrentClassSuccess(data)
                    view.closeProgressBar()
                }

                override fun onError(s: String?) {
                    iKidApplications.log(TAG, s!!)
                    view.getCurrentClassError(s)
                    view.closeProgressBar()
                }

            }
    }

    fun getUser() {
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        if (token != null) {
            object : GetObjectByKeyModel<DataUser>(DataUser::class.java, "token", token) {
                override fun onSuccess(`object`: DataUser?) {
                    if (`object` != null) {
                        view.setDataUser(`object`)
                    }
                }
            }
        }
    }

    fun saveCurrentClass(data: ClassCurrentTeacher) {
        SharedUtils.getInstance().putIntValue(Constants.CURRENT_CLASS_TEACHER_ID, data.id)
        object : SaveObjectModel<ClassCurrentTeacher>(data) {
            override fun onSuccess() {
                iKidApplications.log(TAG, "saveCurrentClass success!")
                view.onSaveClassSuccess()
            }

            override fun onError() {
                iKidApplications.log(TAG, "saveCurrentClass error!")
                view.onSaveClassError()
            }

        }
    }

    fun initDrawer() {
        val listDrawer = ArrayList<ItemDrawer>()
        listDrawer.add(ItemDrawer(Constants.DRAWER_EDIT_ACC, "Sửa thông tin tài khoản", R.mipmap.edit_account))
        listDrawer.add(ItemDrawer(Constants.DRAWER_CHANGEPASS, "Đổi mật khẩu", R.mipmap.change_pass))
        listDrawer.add(ItemDrawer(Constants.DRAWER_ABOUT, "Thông tin về iKidz", R.mipmap.about_us))
        listDrawer.add(ItemDrawer(Constants.DRAWER_LOGOUT, "Đăng xuất", R.mipmap.logout))
        view.initDrawerSuccess(listDrawer)
    }
}
