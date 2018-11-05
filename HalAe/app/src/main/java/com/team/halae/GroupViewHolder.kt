package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class GroupViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var groupImage : ImageView = itemView!!.findViewById(R.id.halmate_group_image) as ImageView
    var groupName : TextView = itemView!!.findViewById(R.id.halmate_group_name) as TextView
}