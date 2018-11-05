package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class SearchActivityViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)  {
    var activityImg : ImageView = itemView!!.findViewById(R.id.activity_img)
    var activityTitle : TextView = itemView!!.findViewById(R.id.activity_title)
}