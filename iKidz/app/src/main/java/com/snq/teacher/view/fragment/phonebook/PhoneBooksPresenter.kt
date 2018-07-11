package com.snq.teacher.view.fragment.phonebook

import com.snq.teacher.R
import com.snq.teacher.model.RESP.RESP_PhoneBook
import com.snq.teacher.model.entity.ClassPeopleEntity
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.server.GetListPhoneBook
import com.snq.teacher.sdk.Utils.NetworkUtils
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