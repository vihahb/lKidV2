package com.snq.teacher.view.activity.home

import android.Manifest
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.snq.teacher.R
import com.snq.teacher.model.RESP.RESP_CurrentClassTeacher
import com.snq.teacher.model.entity.CountNotify
import com.snq.teacher.model.entity.DataUser
import com.snq.teacher.model.entity.ItemDrawer
import com.snq.teacher.presenter.HomePresenter
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.PermissionHelper
import com.snq.teacher.sdk.Utils.SharedUtils
import com.snq.teacher.sdk.Utils.TextUtils
import com.snq.teacher.sdk.Utils.WidgetUtils
import com.snq.teacher.sdk.callback.ItemClickListener
import com.snq.teacher.view.activity.editProfile.EditProfileActivity
import com.snq.teacher.view.activity.login.LoginActivity
import com.snq.teacher.view.adapter.viewpager.ViewPagerMainAdapter
import com.snq.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import java.util.*

class HomeActivity : IHomeView, BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun getSetNotify(data: CountNotify) {
        SharedUtils.getInstance().putIntValue(Constants.ABSENT, data.leaveSchool)
        SharedUtils.getInstance().putIntValue(Constants.ADD_DRUG, data.addDrug)
        SharedUtils.getInstance().putIntValue(Constants.register_more_time, data.registerMoreTime)
    }

    override fun initDrawerSuccess(data: ArrayList<ItemDrawer>) {
        listDrawer.addAll(data)
        adapterDrawer.notifyDataSetChanged()
    }

    override fun onSaveClassSuccess() {
        showLongToast("Đã lưu thông tin lớp chủ nhiệm")
    }

    override fun onSaveClassError(errorMessage: String) {
//        showLongToast("Đã lưu thông tin lớp chủ nhiệm")
    }

    override fun getCurrentClassSuccess(data: RESP_CurrentClassTeacher) {
        presenter.saveCurrentClass(data.data)
    }

    override fun getCurrentClassError(s: String) {
        showLongToast(s)
    }

    override fun setDataUser(dataUser: DataUser) {
        if (!TextUtils.isEmpty(dataUser.user.fullName))
            tv_user_name.text = dataUser.user.fullName
        if (!TextUtils.isEmpty(dataUser.user.username))
            tv_user_acc.text = "Tài khoản: " + dataUser.user.username

        WidgetUtils.setImageURL(imgAvatar, dataUser.user.avatar, R.mipmap.ic_launcher_round)
    }

    lateinit var titles: String

    private lateinit var imgAvatar: ImageView
    lateinit var tv_user_name: TextView
    lateinit var tv_user_acc: TextView
    lateinit var rcl_drawer: RecyclerView

    lateinit var presenter: HomePresenter
    lateinit var listDrawer: ArrayList<ItemDrawer>

    var viewpagerAdapter: ViewPagerMainAdapter? = null
    lateinit var adapterDrawer: AdapterDrawer
    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(this)
        initToolbar(resources.getString(R.string.title_newsfeed), false)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        initFragment()
        checkPermission()
        titles = resources.getString(R.string.toolbar_title_newsfeed)
        setTitleToolbar(resources.getString(R.string.toolbar_title_newsfeed))
        initHeaderView()
    }

    private fun initHeaderView() {
        var headerView = nav_view.getHeaderView(0)
        listDrawer = ArrayList()

        imgAvatar = headerView.findViewById(R.id.img_avatar)
        tv_user_name = headerView.findViewById(R.id.tv_user_name)
        tv_user_acc = headerView.findViewById(R.id.tv_user_acc)
        rcl_drawer = headerView.findViewById(R.id.rcl_drawer)
        rcl_drawer.layoutManager = LinearLayoutManager(this)
        adapterDrawer = AdapterDrawer(listDrawer, activity, ItemClickListener { id ->
            when (id) {
                Constants.DRAWER_EDIT_ACC -> {
                    startActivity(EditProfileActivity::class.java)
                }
                Constants.DRAWER_CHANGEPASS -> {

                }
                Constants.DRAWER_ABOUT -> {

                }
                Constants.DRAWER_LOGOUT -> {
                    SharedUtils.getInstance().clearData()
                    startActivityAndFinish(LoginActivity::class.java)
                }
            }
        })
        rcl_drawer.adapter = adapterDrawer
        presenter.initDrawer()
        presenter.getUser()
    }

    private fun checkPermission() {
        if (!PermissionHelper.checkPermission(permissions, this, 505)) {
            showLongToast(getString(R.string.message_permission_needed))
        }
    }

    private fun initFragment() {
        viewpagerAdapter = ViewPagerMainAdapter(supportFragmentManager, this)
        viewPagerMain.adapter = viewpagerAdapter
        tablayoutMain.setupWithViewPager(viewPagerMain)
        viewPagerMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (viewpagerAdapter != null)
                    titles = resources.getString(R.string.toolbar_title_newsfeed)

                when (position) {
                    0 -> {
                        titles = resources.getString(R.string.toolbar_title_newsfeed)
                    }
                    1 -> {
                        titles = resources.getString(R.string.toolbar_title_school)
                    }
                    2 -> {
                        titles = resources.getString(R.string.toolbar_title_contacts)
                    }
                    3 -> {
                        titles = resources.getString(R.string.toolbar_title_phonebooks)
                    }
                    4 -> {
                        titles = resources.getString(R.string.toolbar_title_message)
                    }
                }

                setTitleToolbar(titles)
            }

        })

        tablayoutMain.enableAnimation(false)
        tablayoutMain.enableShiftingMode(false)
        tablayoutMain.enableItemShiftingMode(false)
        tablayoutMain.setupWithViewPager(viewPagerMain)
        initBottomNavListener()
    }

    private fun initBottomNavListener() {
        tablayoutMain.onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_news -> {

                }
                R.id.nav_school -> {

                }
                R.id.nav_contacts -> {

                }
                R.id.nav_phonebooks -> {

                }
                R.id.nav_message -> {

                }
            }
            true
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            showConfirmExitApp()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 505) {
            if (!PermissionHelper.checkResult(grantResults)) {
                showLongToast(getString(R.string.message_permission_needed))
            }
        }
    }
}
