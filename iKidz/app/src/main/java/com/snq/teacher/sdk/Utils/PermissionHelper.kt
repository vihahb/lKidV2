package com.snq.teacher.sdk.Utils

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

object PermissionHelper {
    fun isAllowPermission(permission: String, activity: Activity): Boolean {

        return ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun isAllowPermission(permission: Array<String>, activity: Activity): Boolean {

        for (i in permission.size - 1 downTo 0) {
            if (ActivityCompat.checkSelfPermission(activity, permission[i]) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }

        return true
    }

    fun checkPermission(permission: Array<String>, activity: Activity, REQUEST_CODE: Int): Boolean {
        var isAllow = true

        for (i in permission.size - 1 downTo 0) {
            if (ActivityCompat.checkSelfPermission(activity, permission[i]) != PackageManager.PERMISSION_GRANTED) {
                isAllow = false
                break
            }
        }

        if (!isAllow) {
            var isShould = true
            for (i in permission.size - 1 downTo 0) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission[i])) {
                    isShould = false
                    break
                }
            }
            // Should we show an explanation?
            if (isShould) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(activity, permission, REQUEST_CODE)
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity, permission, REQUEST_CODE)
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return false
        }

        return true
    }

    fun checkPermission(permission: String, activity: Activity, REQUEST_CODE: Int): Boolean {
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(activity, arrayOf(permission), REQUEST_CODE)
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity, arrayOf(permission), REQUEST_CODE)
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return false
        }
        return true
    }

    fun checkResult(grantResults: IntArray): Boolean {

        for (i in grantResults.indices.reversed()) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED)
                return false
        }

        return true
    }

    fun checkOnlyResult(grantResults: IntArray, position: Int): Boolean {
        return grantResults[position] != PackageManager.PERMISSION_DENIED
    }
}