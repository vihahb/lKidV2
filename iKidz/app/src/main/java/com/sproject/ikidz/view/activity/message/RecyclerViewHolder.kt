package com.sproject.ikidz.view.activity.message

import android.support.v7.widget.RecyclerView
import android.view.View

open class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private var onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener? = null

    fun setOnRecyclerViewItemClickListener(listener: OnRecyclerViewItemClickListener?) {
        this.onRecyclerViewItemClickListener = listener
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (onRecyclerViewItemClickListener != null) {
            onRecyclerViewItemClickListener!!.onRecyclerViewItemClick(view, this.adapterPosition)
        }
    }
}
