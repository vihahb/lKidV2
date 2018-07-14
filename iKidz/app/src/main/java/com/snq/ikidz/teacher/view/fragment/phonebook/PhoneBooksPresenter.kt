package com.snq.ikidz.teacher.view.fragment.phonebook

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.RESP.RESP_PhoneBook
import com.snq.ikidz.teacher.model.entity.ClassPeopleEntity
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.server.GetListPhoneBook
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
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