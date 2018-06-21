package com.sproject.ikidz.view.activity.shuttleBus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.ShuttleBusEntity
import com.sproject.ikidz.view.base.BaseActivity

class ShuttlesBusActivity : BaseActivity(), IShuttlesBus {
    override fun getShuttleBusSuccess(data: MutableList<ShuttleBusEntity>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getShuttlesError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shuttles_bus)
        initToolbar("THÔNG TIN ĐƯA ĐÓN", true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
