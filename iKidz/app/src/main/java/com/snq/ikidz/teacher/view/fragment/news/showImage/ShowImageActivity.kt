package com.snq.ikidz.teacher.view.fragment.news.showImage

import android.os.Bundle
import android.view.View
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.entity.AlbumEntity
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_show_image.*
import java.util.*

class ShowImageActivity : BaseActivity() {

    var albumList: ArrayList<AlbumEntity>? = null
    var tempAlbumList: ArrayList<AlbumEntity>? = null
    lateinit var adapter: ViewPagerImageAdapter
    var position = -1

    lateinit var adapterThumbl: AdapterThumble

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)
        albumList = ArrayList()
        tempAlbumList = ArrayList()
        getData()
        img_back.setOnClickListener { finish() }
        initAction()
    }

    private fun initAction() {
        img_prev.setOnClickListener {
            previousImage(true)
        }
        img_next.setOnClickListener {
            previousImage(false)
        }
    }

    private fun previousImage(previous: Boolean) {
        if (previous) {
            if (view_pager_image.currentItem > 0)
                view_pager_image.currentItem = view_pager_image.currentItem - 1
            else
                view_pager_image.currentItem = albumList!!.size - 1

        } else {
            if (view_pager_image.currentItem < albumList!!.size - 1)
                view_pager_image.currentItem = view_pager_image.currentItem + 1
            else
                view_pager_image.currentItem = 0
        }
    }

    private fun getData() {
        position = intent.getIntExtra(Constants.IMAGE_POSITION, -1)
        tempAlbumList = intent.getSerializableExtra(Constants.LIST_ALBUM) as ArrayList<AlbumEntity>?
        if (tempAlbumList != null && tempAlbumList!!.size > 0) {
            albumList!!.addAll(tempAlbumList!!)
            adapter = ViewPagerImageAdapter(supportFragmentManager, albumList)
            view_pager_image.adapter = adapter
            adapterThumbl = AdapterThumble(view_pager_image, albumList, this)
            rcl_thumbl.setUpWithViewPager(view_pager_image)
            rcl_thumbl.adapter = adapterThumbl
            view_pager_image.setCurrentItem(position, true)

            if (albumList!!.size > 1) {
                fr_next.visibility = View.VISIBLE
                fr_prev.visibility = View.VISIBLE
            } else {
                fr_next.visibility = View.GONE
                fr_prev.visibility = View.GONE
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_2_right)
    }

    override fun onDestroy() {
        super.onDestroy()
        albumList = null
        tempAlbumList = null
    }
}
