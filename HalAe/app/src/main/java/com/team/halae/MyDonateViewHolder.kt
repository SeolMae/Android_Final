package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class MyDonateViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var donateTitle : TextView = itemView!!.findViewById(R.id.mydonate_title) as TextView
    var donateHalmate : TextView = itemView!!.findViewById(R.id.mydonate_halmate) as TextView
    var donateAmount : TextView = itemView!!.findViewById(R.id.mydonate_howmuch) as TextView
    var donateDate : TextView = itemView!!.findViewById(R.id.mydonate_date) as TextView
}