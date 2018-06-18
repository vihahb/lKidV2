package com.sproject.ikidz.view.activity.drug.teacher

import android.os.Bundle
import android.view.MenuItem
import com.sproject.ikidz.R
import com.sproject.ikidz.view.base.BaseActivity

class DrugActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drug)
        initToolbar("DẶN THUỐC", true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
