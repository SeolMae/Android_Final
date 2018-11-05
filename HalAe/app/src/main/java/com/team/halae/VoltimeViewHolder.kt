package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class VoltimeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var volDate : TextView = itemView!!.findViewById(R.id.vol_date) as TextView
    var volHalmate  : TextView = itemView!!.findViewById(R.id.vol_halmate) as TextView
    var volAddtime : TextView = itemView!!.findViewById(R.id.vol_addtime) as TextView
    var volTotaltime : TextView = itemView!!.findViewById(R.id.vol_totaltime) as TextView
}
