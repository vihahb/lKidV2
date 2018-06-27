package com.sproject.ikidz.sdk.extension

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent

/**
 * Created by kem on 9/13/17.
 */

fun RecyclerView.addOnItemClickListener(context: Context,
                                        listener: RecyclerItemClickListener)
        : RecyclerView.OnItemTouchListener {

    class RecyclerItemClickListener: RecyclerView.OnItemTouchListener {
        internal var mGestureDetector: GestureDetector

        init {
            mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }
            })
        }

        override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
            val childView = view.findChildViewUnder(e.x, e.y)
            if (childView != null && mGestureDetector.onTouchEvent(e)) {
                listener.onItemClick(childView, view.getChildAdapterPosition(childView))
            }
            return false
        }

        override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }

    val recyclerItemClickListener = RecyclerItemClickListener()
    this.addOnItemTouchListener(recyclerItemClickListener)

    return recyclerItemClickListener
}