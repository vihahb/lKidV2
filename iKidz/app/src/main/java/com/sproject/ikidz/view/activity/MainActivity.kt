package com.sproject.ikidz.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.database.GetObjectByKeyModel
import com.sproject.ikidz.model.entity.DataUser
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.JsonHelper
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.view.activity.home.HomeActivity
import com.sproject.ikidz.view.activity.login.LoginActivity

class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"
    var alreadyExistsUser = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkUser()
    }

    private fun checkUser() {

        var token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        if (token != null) {
            object : GetObjectByKeyModel<DataUser>(DataUser::class.java, "token", token) {
                override fun onError(message: ErrorEntity?) {
                    iKidApplications.log(TAG, "checkUser getObjectError: " + message!!.errorMessage)
                }

                override fun onSuccess(`object`: DataUser?) {
                    iKidApplications.log(TAG, "checkUser getObjectSuccess: " + JsonHelper.toJson(`object`))
                    alreadyExistsUser = `object` != null
                }
            }
        } else {
            alreadyExistsUser = false
        }

        if (alreadyExistsUser)
            Handler().postDelayed({
                this.startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }, 3000)
        else
            Handler().postDelayed({
                this.startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, 3000)

    }
}
