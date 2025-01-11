package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.FriendAdapter
import com.example.myapplication.model.Friend

class FriendListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friend_list)


        val friendList = listOf(
            Friend(1, "김민지", "online"),
            Friend(2, "한희태", "offline"),
            Friend(3, "넙죽이", "offline")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.friendRecyclerView)
        recyclerView.adapter = FriendAdapter(friendList)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}