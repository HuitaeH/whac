package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Friend

class FriendAdapter(private val friends: List<Friend>) :
    RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {

    class FriendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profileImage: ImageView = view.findViewById(R.id.profileImage)
        val name: TextView = view.findViewById(R.id.friendName)
        val status: TextView = view.findViewById(R.id.status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friend, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friend = friends[position]
        holder.name.text = friend.name
        holder.status.text = friend.status
        friend.profileImage?.let {
            holder.profileImage.setImageResource(it)
        }
    }

    override fun getItemCount() = friends.size
}