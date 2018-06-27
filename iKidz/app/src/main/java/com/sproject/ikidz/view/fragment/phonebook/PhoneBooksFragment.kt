package com.sproject.ikidz.view.fragment.phonebook

import android.app.Dialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.ClassPeopleEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.RoundImage
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.sdk.Utils.WidgetUtils
import com.sproject.ikidz.sdk.callback.ItemMoreActionGeneric
import com.sproject.ikidz.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_phonebooks.*
import java.util.*

class PhoneBooksFragment : BaseFragment(), IPhoneBooks {
    override fun getPhoneBookSuccess(dataList: ArrayList<ClassPeopleEntity>) {
        if (dataList.isNotEmpty()) {
            list.clear()
            list.addAll(dataList)
            tv_message.visibility = View.INVISIBLE
        } else {
            tv_message.visibility = View.VISIBLE
            tv_message.text = activity!!.resources.getString(R.string.mesage_no_data)
        }
        adapter.notifyDataSetChanged()
    }

    override fun getPhoneBookError(errorMessage: String) {
        tv_message.text = errorMessage
    }

    companion object {

        fun newInstance(): PhoneBooksFragment {
            val args = Bundle()
            val fragment = PhoneBooksFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var presenter: PhoneBooksPresenter
    lateinit var adapter: AdapterPhoneBook
    lateinit var list: ArrayList<ClassPeopleEntity>
    lateinit var dialog: Dialog
    lateinit var tv_name: TextView
    lateinit var tv_birthday: TextView
    lateinit var tv_relation: TextView
    lateinit var img_avatar: RoundImage

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_phonebooks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = PhoneBooksPresenter(this)
        initView()
    }

    private fun initView() {
        list = ArrayList()
        adapter = AdapterPhoneBook(list, context, object : ItemMoreActionGeneric<ClassPeopleEntity> {
            override fun ItemClick(type: Int, id: Int, data: ClassPeopleEntity) {
                when (type) {
                    Constants.TYPE_VIEW -> {
                        showDialog(data)
                    }
                    Constants.TYPE_EDIT -> {
//                        showUpdateDialog(data)
                    }
                }
            }
        })
        rcl_phone_book.layoutManager = LinearLayoutManager(context)
        rcl_phone_book.adapter = adapter
        presenter.getPhoneBook()

        initDialog()
    }

    private fun showDialog(data: ClassPeopleEntity) {

        if (!TextUtils.isEmpty(data.avatar)) {
            WidgetUtils.setImageURL(img_avatar, data.avatar, R.mipmap.ic_no_avatar)
        }

        if (!TextUtils.isEmpty(data.relationshipName)) {
            tv_relation.text = data.relationshipName + " của bé " + data.first_name_student
        } else {
            if (data.gender == "0") {
                tv_relation.text = "Cô giáo"
            } else {
                tv_relation.text = "Thầy giáo"
            }
        }

        if (!TextUtils.isEmpty(data.fullName)) {
            tv_name.text = data.fullName
        }

        if (!TextUtils.isEmpty(data.birthday)) {
            tv_birthday.text = TimeUtils.formatDate(data.birthday, "yyyy-mm-dd", "dd-mm-yyyy")
        }
        if (!dialog.isShowing)
            dialog.show()
    }

    private fun initDialog() {
        dialog = Dialog(context, R.style.Theme_Transparent)
        dialog.setContentView(R.layout.dialog_view_detail_people)
//        Objects.requireNonNull<Window>(dialog.window).attributes.windowAnimations = R.style.DialogAnim
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        tv_name = dialog.findViewById<TextView>(R.id.tv_name)
        tv_birthday = dialog.findViewById<TextView>(R.id.tv_birthday)
        tv_relation = dialog.findViewById<TextView>(R.id.tv_relation)
        img_avatar = dialog.findViewById<RoundImage>(R.id.img_avatar)
        val btnClose = dialog.findViewById<Button>(R.id.btnClose)

        btnClose.setOnClickListener {
            dialog.dismiss()
        }
    }
}