package com.snq.ikidz.teacher.view.activity.message.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.snq.ikidz.teacher.iKidApplications
import java.io.Serializable

class ChatBroadcast : BroadcastReceiver {

    var listener: Listener? = null

    constructor() {
        listener = null
    }

    constructor(listener: Listener) {
        this.listener = listener
    }

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1 != null) {
            listener?.onReceive(p1)
        }
    }

    interface Listener {
        fun onReceive(intent: Intent)
    }

    companion object {

        val addedRoom = "com.kesu.added_room"
        val addedMessage = "com.kesu.added_message"

        fun getFilter(): IntentFilter {
            val filter = IntentFilter(addedMessage)
            filter.addAction(addedRoom)
            return filter
        }

        fun send(action: String, data: HashMap<String, Any>? = null) {
            val intent = Intent()
            intent.action = action
            val keys = data?.keys ?: mutableSetOf<String>()
            for (key in keys) {
                val value = data?.get(key)
                if (value is Serializable) {
                    intent.putExtra(key, value)
                }
            }
            iKidApplications.context!!.sendBroadcast(intent)
        }
    }
}