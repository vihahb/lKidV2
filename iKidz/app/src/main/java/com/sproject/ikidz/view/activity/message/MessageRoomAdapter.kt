package com.sproject.ikidz.view.activity.message

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.message.MessageRoom
import com.sproject.ikidz.model.entity.message.RoomType
import com.sproject.ikidz.model.entity.message.User
import com.sproject.ikidz.sdk.Utils.RoundImage
import com.sproject.ikidz.sdk.Utils.WidgetUtils
import com.sproject.ikidz.sdk.extension.RecyclerItemClickListener
import com.sproject.ikidz.sdk.extension.timePeriod

class MessageRoomAdapter(private var messageRooms: List<MessageRoom>?, private val context: Context, private var listener: RecyclerItemClickListener) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        if (viewType == 1) {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_list, parent, false)

            return MessageHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_empty, parent, false)
            return EmptyViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        if (holder is MessageHolder) {
            val messageRoom = messageRooms!![position]
            val diameter = context.resources.getDimension(R.dimen.size_56).toInt()
            if (messageRoom.type == RoomType.private) {
                for (user in messageRoom.fbUsers) {
                    if (user.id == User.current?.fbUser?.id) {
                        continue
                    }
                    holder.tv_username?.text = user.name

                    WidgetUtils.setImageURL(holder.imv_avatar, user.avatarUrl, R.mipmap.ic_launcher_round)
                    break
                }
            } else {
                holder.tv_username!!.text = messageRoom.name
                WidgetUtils.setImageURL(holder.imv_avatar, messageRoom.avatar, R.mipmap.ic_launcher_round)
            }
            val lastMessage = messageRoom.messages.lastOrNull()

            if (lastMessage != null) {
                val timeString = lastMessage.date?.timePeriod() ?: ""
                holder.tv_time?.text = timeString
                holder.tv_message?.text = lastMessage.message
            } else {
                val timeString = messageRoom.createdDate?.timePeriod() ?: ""
                holder.tv_time?.text = timeString
                holder.tv_message?.text = ""
            }
            holder.itemView.setOnClickListener {
                listener.onItemClick(holder.itemView, position)
            }
        }
    }

    override fun getItemCount(): Int {
        val size = messageRooms?.size ?: 0
        return if (size == 0) 1 else size
    }

    override fun getItemViewType(position: Int): Int {
        val size = messageRooms?.size ?: 1
        return if (size == 0) 0 else 1
    }

    fun setData(messageRooms: List<MessageRoom>) {
        this.messageRooms = messageRooms
        notifyDataSetChanged()
    }

    inner class MessageHolder(view: View) : RecyclerViewHolder(view) {
        var tv_username: TextView? = null
        var tv_time: TextView? = null
        var tv_message: TextView? = null
        var imv_avatar: RoundImage? = null
        var rel_unread: RelativeLayout? = null

        init {
            tv_username = view.findViewById(R.id.tv_username)
            tv_time = view.findViewById(R.id.tv_time)
            tv_message = view.findViewById(R.id.tv_message)
            imv_avatar = view.findViewById(R.id.imv_avatar)
            rel_unread = view.findViewById(R.id.rel_unread)
        }
    }
}