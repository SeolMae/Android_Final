package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class SearchVolunteerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)  {
    var volunteerImg : ImageView = itemView!!.findViewById(R.id.volunteer_img) as ImageView
    var volunteerName : TextView = itemView!!.findViewById(R.id.volunteer_name_text) as TextView
}