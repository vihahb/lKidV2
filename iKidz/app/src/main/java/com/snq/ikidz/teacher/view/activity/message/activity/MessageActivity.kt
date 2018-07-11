package com.snq.ikidz.teacher.view.activity.message.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.RESP.RESP_ListParent
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.entity.message.MessageRoom
import com.snq.ikidz.teacher.model.entity.message.Parent
import com.snq.ikidz.teacher.model.entity.message.User
import com.snq.ikidz.teacher.model.server.GetListParent
import com.snq.ikidz.teacher.sdk.extension.RecyclerItemClickListener
import com.snq.ikidz.teacher.view.activity.message.MessageRoomAdapter
import com.snq.ikidz.teacher.view.activity.message.broadcast.ChatBroadcast
import com.snq.ikidz.teacher.view.base.BaseActivity

class MessageActivity : BaseActivity(), ChatBroadcast.Listener, SwipeRefreshLayout.OnRefreshListener {

    private var recyclerView: RecyclerView? = null
    private var srlLayout: SwipeRefreshLayout? = null
    private var adapter: MessageRoomAdapter? = null
    private var messageRooms: MutableList<MessageRoom>? = null
    var broadcast: ChatBroadcast? = null
    var childId = -1

    companion object {
        var childIdKey = "CHILD_ID"

        fun Initialize(intent: Intent, childId: Int) {
            intent.putExtra(childIdKey, childId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_detail)
        initToolbar("TIN NHẮN", true)
        initUI()


        broadcast = ChatBroadcast(this)
        baseContext.registerReceiver(broadcast, ChatBroadcast.getFilter())

        childId = intent.getIntExtra(childIdKey, -1)
        if (childId > 0) {
            messageRooms = mutableListOf()
            loadData(childId)
        } else {
            messageRooms = User.current?.fbUser?.rooms ?: mutableListOf()
        }

        adapter = MessageRoomAdapter(messageRooms, applicationContext, object : RecyclerItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val messageRoom = messageRooms?.get(position)
                if (messageRoom != null) {

//                    val intent = Intent(baseContext, MessageDetailActivity::class.java)
//                    MessageDetailActivity.Initialize(intent, messageRoom)
//                    startActivity(intent)
                }
            }
        })
        val messageLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.layoutManager = messageLayoutManager
        recyclerView!!.adapter = adapter
        recyclerView!!.isNestedScrollingEnabled = false
        val dividerItemDecoration = DividerItemDecoration(recyclerView!!.context, 1)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.divider_short_gray)!!)
        recyclerView!!.addItemDecoration(dividerItemDecoration)
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        baseContext.unregisterReceiver(broadcast)
    }

    override fun onReceive(intent: Intent) {
        adapter?.notifyDataSetChanged()
    }

    private fun initUI() {
        recyclerView = findViewById(R.id.rcv_messages)
        srlLayout = findViewById(R.id.srlLayout)
        srlLayout!!.setColorSchemeResources(R.color.colorPrimary)
        srlLayout!!.setOnRefreshListener(this)
    }

    private fun loadData(childId: Int) {
        srlLayout!!.isRefreshing = true

        object : GetListParent(childId) {
            override fun onSucces(data: RESP_ListParent?) {
                val parents = data as? ArrayList<Parent>
                val total = parents?.count() ?: 0
                for (i in 0 until total) {
                    val room = MessageRoom.initRoom(parents?.get(i))
                    if (room != null) {
                        messageRooms?.add(room)
                    }
                }
                adapter?.notifyDataSetChanged()
                srlLayout!!.isRefreshing = false
            }

            override fun onError(s: ErrorEntity?) {
                showLongToast(s!!.errorMessage)
                srlLayout!!.isRefreshing = false
            }

        }

//        childService.getParent(childId, object : Callback {
//            override fun onError(code: Int, message: String) {
//                showLongToast(message)
//                srlLayout!!.isRefreshing = false
//            }
//
//            override fun onSuccess(data: Any?) {
//                val parents = data as? ArrayList<Parent>
//                val total = parents?.count() ?: 0
//                for (i in 0 until total) {
//                    val room = MessageRoom.initRoom(parents?.get(i))
//                    if (room != null) {
//                        messageRooms?.add(room)
//                    }
//                }
//                adapter?.notifyDataSetChanged()
//                srlLayout!!.isRefreshing = false
//            }
//        })

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.messages_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    override fun onRefresh() {
        if (childId > 0) {
            loadData(childId)
        }
    }
}