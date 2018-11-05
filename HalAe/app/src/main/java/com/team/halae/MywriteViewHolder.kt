package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MywriteViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var mywriteImg : ImageView = itemView!!.findViewById(R.id.mywrite_image) as ImageView
    var mywriteTitle : TextView = itemView!!.findViewById(R.id.mywrite_title) as TextView
    var mywriteHalmate : TextView = itemView!!.findViewById(R.id.mywrite_halmate) as TextView
}