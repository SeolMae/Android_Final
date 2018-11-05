package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class DonateListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var donateImg : ImageView = itemView!!.findViewById(R.id.donate_halmate_img)
    var donateTitle : TextView = itemView!!.findViewById(R.id.donate_title)
    var donateHalmatename : TextView = itemView!!.findViewById(R.id.donate_halmate_name)
    var donateGoalmoney : TextView = itemView!!.findViewById(R.id.donate_goal_money)
    var donatePercent : TextView = itemView!!.findViewById(R.id.donate_percent)
    var donateLeftdays : TextView = itemView!!.findViewById(R.id.donate_left_days)
}