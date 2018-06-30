package com.sproject.ikidz.view.fragment.phonebook

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_PhoneBook
import com.sproject.ikidz.model.entity.ClassPeopleEntity
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetListPhoneBook
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import java.util.*

class PhoneBooksPresenter(private var view: IPhoneBooks) {
    fun getPhoneBook() {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
//        view.showProgressBar(true, true, "Loading...")
        object : GetListPhoneBook() {
            override fun onSucces(respond: RESP_PhoneBook?) {
                var dataList = ArrayList<ClassPeopleEntity>()
                dataList.addAll(respond!!.data[0].teachers)
                dataList.add(ClassPeopleEntity(true))
                dataList.addAll(respond.data[0].parents)
                view.getPhoneBookSuccess(dataList)
//                view.closeProgressBar()
            }

            override fun onError(s: ErrorEntity) {
//                view.closeProgressBar()
                view.getPhoneBookError(s.errorMessage)
            }
        }
    }
}