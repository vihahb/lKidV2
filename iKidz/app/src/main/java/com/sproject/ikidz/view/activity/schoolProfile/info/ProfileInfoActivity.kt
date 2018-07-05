package com.sproject.ikidz.view.activity.schoolProfile.info

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.*
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.sdk.Utils.WidgetUtils
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_profile_info.*
import java.util.*

class ProfileInfoActivity : BaseActivity(), IProfileView {
    override fun updateProfileSuccess() {
        showLongToast("Cập nhật thông tin học bạ thành công!")
        finish()
    }

    override fun updateProfileError() {
        showLongToast("Cập nhật thông tin học bạ không thành công!")
    }

    override fun getSampleReviewError() {
        showLongToast("Lấy thông tin danh hiệu thất bại. Vui lòng thử lại sau!")
    }

    override fun getSampleReviewSuccess(data: List<String>) {
        if (!title1.isEmpty())
            title1.clear()
        title1.addAll(data)
        adapter1.notifyDataSetChanged()

        if (!title2.isEmpty())
            title2.clear()
        title2.addAll(data)
        adapter2.notifyDataSetChanged()

        if (!titleTotla.isEmpty())
            titleTotla.clear()
        titleTotla.addAll(data)
        adapterTotal.notifyDataSetChanged()
    }

    override fun getProfileSuccess(data: ProfileInfoEntify?) {
        dataProfile = data!!
        initData(data)
    }

    override fun getProfileError() {
        showLongToast("Tải học bạ không thành công!")
    }

    lateinit var dataProfile: ProfileInfoEntify

    lateinit var entity: ProfileEntity
    lateinit var presenter: ProfilePresenter
    lateinit var title1: ArrayList<String>
    lateinit var title2: ArrayList<String>
    lateinit var titleTotla: ArrayList<String>

    lateinit var adapter1: AdapterSpinnerTitle
    lateinit var adapter2: AdapterSpinnerTitle
    lateinit var adapterTotal: AdapterSpinnerTitle

    lateinit var uptoList: ArrayList<UpToClassEntity>
    lateinit var adapterUpto: AdapterSpinnerUpto

    var title_1 = ""
    var title_2 = ""
    var title_year = ""
    var upToClass = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)
        initToolbar("NHẬN XÉT HỌC BẠ", true)
        presenter = ProfilePresenter(this)
        getData()
        initView()
    }

    private fun getData() {
        entity = intent.getSerializableExtra(Constants.OBJECT) as ProfileEntity
        if (entity != null) {
            presenter.getProfile(entity.id)
        }
    }

    private fun initData(entity: ProfileInfoEntify) {
        if (!entity.avatar.isNullOrEmpty()) {
            WidgetUtils.setImageURL(img_avatar, entity.avatar, R.mipmap.ic_no_avatar)
        }

        if (!entity.fullName.isNullOrEmpty()) {
            tv_name.text = entity.fullName
        }

        if (!entity.birthday.isNullOrEmpty()) {
            tv_date.text = entity.birthday
        }
        if (!entity.className.isNullOrEmpty()) {
            tv_class_name.text = entity.className
        }
        if (!entity.yearName.isNullOrEmpty()) {
            tv_year.text = entity.yearName
        }

        if (!entity.absentAllow1.isNullOrEmpty()) {
            edt_semester_1_absent.setText(entity.absentAllow1)
        }

        if (!entity.absentNotAllow1.isNullOrEmpty()) {
            edt_semester_1_not_absent.setText(entity.absentNotAllow1)
        }
        if (!entity.absentAllow2.isNullOrEmpty()) {
            edt_semester_2_absent.setText(entity.absentAllow2)
        }

        if (!entity.absentNotAllow2.isNullOrEmpty()) {
            edt_semester_2_not_absent.setText(entity.absentNotAllow2)
        }

        if (!entity.absentYear.isNullOrEmpty()) {
            edt_total_absent.setText(entity.absentYear)
        }

        if (!entity.reviewMorality.isNullOrEmpty())
            edt_daoduc.setText(entity.reviewMorality)

        if (!entity.reviewHealth.isNullOrEmpty())
            edt_suckhoe.setText(entity.reviewHealth)

        if (!entity.reviewStudy.isNullOrEmpty())
            edt_hoctap.setText(entity.reviewStudy)

        if (!entity.reviewYear.isNullOrEmpty())
            edt_comment_total.setText(entity.reviewYear)

        title1.forEachIndexed { index, s ->
            if (s == entity.title1)
                sp_danh_hieu_semester_1.setSelection(index)
        }
        title2.forEachIndexed { index, s ->
            if (s == entity.title2)
                sp_danh_hieu_semester_2.setSelection(index)
        }
        titleTotla.forEachIndexed { index, s ->
            if (s == entity.titleYear)
                sp_danh_hieu_total.setSelection(index)
        }
    }

    private fun initView() {
        uptoList = ArrayList()
        uptoList.add(UpToClassEntity(1, "Được lên lớp"))
        uptoList.add(UpToClassEntity(0, "Không được lên lớp"))
        adapterUpto = AdapterSpinnerUpto(this, uptoList)
        sp_lenlop.adapter = adapterUpto
        sp_lenlop.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                upToClass = uptoList[p2].id
            }

        }

        title1 = ArrayList()
        title2 = ArrayList()
        titleTotla = ArrayList()

        adapter1 = AdapterSpinnerTitle(this@ProfileInfoActivity, title1)
        adapter2 = AdapterSpinnerTitle(this@ProfileInfoActivity, title2)
        adapterTotal = AdapterSpinnerTitle(this@ProfileInfoActivity, titleTotla)

        sp_danh_hieu_semester_1.adapter = adapter1
        sp_danh_hieu_semester_1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                title_1 = title1[p2]
            }

        }

        sp_danh_hieu_semester_2.adapter = adapter2
        sp_danh_hieu_semester_2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                title_2 = title2[p2]
            }

        }

        sp_danh_hieu_total.adapter = adapterTotal
        sp_danh_hieu_total.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                title_year = titleTotla[p2]
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile_school, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        else if (item.itemId == R.id.save_action)
            saveProfileInfo()
        return super.onOptionsItemSelected(item)
    }

    private fun saveProfileInfo() {
        if (sp_danh_hieu_semester_1.selectedItemPosition >= 0) {
            title_1 = title1[sp_danh_hieu_semester_1.selectedItemPosition]
        } else {
            showLongToast("Vui lòng chọn danh hiệu kỳ 1")
            return
        }

        if (sp_danh_hieu_semester_2.selectedItemPosition >= 0) {
            title_2 = title2[sp_danh_hieu_semester_2.selectedItemPosition]
        } else {
            showLongToast("Vui lòng chọn danh hiệu kỳ 2")
            return
        }

        if (sp_danh_hieu_total.selectedItemPosition >= 0) {
            title_year = titleTotla[sp_danh_hieu_total.selectedItemPosition]
        } else {
            showLongToast("Vui lòng chọn danh hiệu cả năm")
            return
        }

        if (sp_lenlop.selectedItemPosition >= 0) {
            upToClass = uptoList[sp_lenlop.selectedItemPosition].id
        } else {
            showLongToast("Vui lòng chọn được lên lớp hoặc không được lên lớp!")
            return
        }

        if (edt_semester_1_absent.text.isNullOrEmpty()) {
            showLongToast("Vui lòng nhập ngày nghỉ có phép học kỳ 1. Nếu bằng không thì nhập là 0!")
            return
        }

        if (edt_semester_2_absent.text.isNullOrEmpty()) {
            showLongToast("Vui lòng nhập ngày nghỉ có phép học kỳ 2. Nếu bằng không thì nhập là 0!")
            return
        }

        if (edt_semester_1_not_absent.text.isNullOrEmpty()) {
            showLongToast("Vui lòng nhập ngày nghỉ không phép học kỳ 1. Nếu bằng không thì nhập là 0!")
            return
        }
        if (edt_semester_2_not_absent.text.isNullOrEmpty()) {
            showLongToast("Vui lòng nhập ngày nghỉ không phép học kỳ 2. Nếu bằng không thì nhập là 0!")
            return
        }

        if (edt_total_absent.text.isNullOrEmpty()) {
            showLongToast("Vui lòng nhập tổng số ngày nghỉ cả năm. Nếu bằng không thì nhập là 0!")
            return
        }
        if (edt_daoduc.text.isNullOrEmpty()) {
            showLongToast("Vui lòng nhập đánh giá đạo đức học sinh!")
            return
        }
        if (edt_hoctap.text.isNullOrEmpty()) {
            showLongToast("Vui lòng nhập đánh giá học tập học sinh!")
            return
        }
        if (edt_suckhoe.text.isNullOrEmpty()) {
            showLongToast("Vui lòng nhập đánh giá sức khỏe học sinh!")
            return
        }
        if (edt_comment_total.text.isNullOrEmpty()) {
            showLongToast("Vui lòng nhập kết luận chung cả học kỳ của học sinh!")
            return
        }

        var value = ProfilePostEntify(
                edt_semester_1_absent.text.toString().toInt(),
                edt_semester_1_absent.text.toString().toInt(),
                edt_semester_1_not_absent.text.toString().toInt(),
                edt_semester_2_not_absent.text.toString().toInt(),
                edt_total_absent.text.toString().toInt(), null,
                edt_suckhoe.text.toString(), edt_daoduc.text.toString(),
                edt_hoctap.text.toString(),
                edt_comment_total.text.toString(), title_1, title_2, title_year, upToClass.toString()
        )

        var arrayList = ArrayList<ProfilePostEntify>()
        arrayList.add(value)

        var data = DataProfilePostEntify()
        data.classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        data.fullName = dataProfile.fullName
        data.studentId = dataProfile.id.toString()
        data.schoolReports = arrayList

        presenter.postProfile(data)
    }
}
