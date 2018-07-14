package com.snq.ikidz.teacher.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.database.GetObjectByKeyModel
import com.snq.ikidz.teacher.model.entity.DataUser
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.JsonHelper
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils
import com.snq.ikidz.teacher.view.activity.home.HomeActivity
import com.snq.ikidz.teacher.view.activity.login.LoginActivity

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
