package com.sproject.ikidz.sdk.Utils

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

object BlurUtils {
    /**
     * Dùng cho API 17 trở lên
     */

    private val BITMAP_SCALE = 0.4f
    //    Càng giản thì càng nét
    private val BLUR_RADIUS = 2f

    fun blur(context: Context, image: Bitmap): Bitmap {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val width = Math.round(image.width * BITMAP_SCALE)
            val height = Math.round(image.height * BITMAP_SCALE)

            val inputBitmap = Bitmap.createScaledBitmap(image, width, height, false)
            val outputBitmap = Bitmap.createBitmap(inputBitmap)

            val rs = RenderScript.create(context)
            val theIntrinsic: ScriptIntrinsicBlur
            theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
            val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
            val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
            theIntrinsic.setRadius(BLUR_RADIUS)
            theIntrinsic.setInput(tmpIn)
            theIntrinsic.forEach(tmpOut)
            tmpOut.copyTo(outputBitmap)

            return outputBitmap
        } else {
            return image
        }
    }
}