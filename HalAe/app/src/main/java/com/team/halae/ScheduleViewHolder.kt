package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ScheduleViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var scheduleName : TextView = itemView!!.findViewById(R.id.schedule_item_name) as TextView
    var scheduleTime : TextView = itemView!!.findViewById(R.id.schedule_item_time) as TextView
    var scheduleItem : View = itemView!!.findViewById(R.id.schedule_item) as View
}