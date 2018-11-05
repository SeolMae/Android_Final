package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class EnterMessageViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var enterMessage : TextView = itemView!!.findViewById(R.id.enter_message) as TextView
}