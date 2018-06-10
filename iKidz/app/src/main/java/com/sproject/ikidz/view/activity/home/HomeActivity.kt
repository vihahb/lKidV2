package com.sproject.ikidz.view.activity.home

import android.Manifest
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.sproject.ikidz.R
import com.sproject.ikidz.sdk.Utils.PermissionHelper
import com.sproject.ikidz.view.activity.home.main_feature.AdapterMainFeature
import com.sproject.ikidz.view.adapter.viewpager.ViewPagerMainAdapter
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var viewpagerAdapter: ViewPagerMainAdapter? = null
    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initToolbar(R.id.toolbar, resources.getString(R.string.title_newsfeed), false)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        initFragment()
        checkPermission()
        setTitleToolbar(resources.getString(R.string.title_newsfeed))
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
                    setTitleToolbar(viewpagerAdapter!!.getPageTitle(position).toString())
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
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
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
