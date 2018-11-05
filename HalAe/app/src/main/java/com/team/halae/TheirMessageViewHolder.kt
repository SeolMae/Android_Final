package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class TheirMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var theirProfile : ImageView = itemView!!.findViewById(R.id.their_message_profile) as ImageView
    var theirName : TextView = itemView!!.findViewById(R.id.their_message_name) as TextView
    var theirMessageText : TextView = itemView!!.findViewById(R.id.their_message_text) as TextView
    var theirMessageTime : TextView = itemView!!.findViewById(R.id.their_message_time) as TextView

}