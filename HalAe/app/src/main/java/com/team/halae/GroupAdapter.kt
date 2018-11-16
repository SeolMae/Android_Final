package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager

class GroupAdapter(var groupItems : ArrayList<HalmateGroupMemarry>, var requestManager: RequestManager) : RecyclerView.Adapter<GroupViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.halmate_group_item, parent, false)
        return GroupViewHolder(mainView)
    }

    override fun getItemCount(): Int = groupItems.size

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        requestManager.load(groupItems[position].usr_img).into(holder.groupImage)
        holder.groupName.text = groupItems[position].usr_name
    }
}