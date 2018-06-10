package com.sproject.ikidz.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.sproject.ikidz.R
import com.sproject.ikidz.view.activity.home.HomeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            this.startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 3000)
    }
}
