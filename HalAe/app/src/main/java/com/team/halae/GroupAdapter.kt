package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class GroupAdapter(var groupItems : ArrayList<GroupItem>) : RecyclerView.Adapter<GroupViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.halmate_group_item, parent, false)
        return GroupViewHolder(mainView)
    }

    override fun getItemCount(): Int = groupItems.size

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.groupImage.setImageResource(groupItems[position].image)
        holder.groupName.text = groupItems[position].name
    }
}