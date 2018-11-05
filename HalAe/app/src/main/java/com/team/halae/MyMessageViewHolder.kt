package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MyMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var myMessageText : TextView = itemView!!.findViewById(R.id.my_message_text) as TextView
    var myMessageTime : TextView = itemView!!.findViewById(R.id.my_message_time) as TextView

}