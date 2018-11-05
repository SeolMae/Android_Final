package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class HalViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var HalImg : ImageView = itemView!!.findViewById(R.id.main_HalIMG) as ImageView
    var HalName : TextView = itemView!!.findViewById(R.id.main_HalName) as TextView
    var HalDD : TextView = itemView!!.findViewById(R.id.main_HalDD) as TextView
    //var HalMsg : ImageView = itemView!!.findViewById(R.id.main_HalMsg) as ImageView
    //var HalCall : ImageView = itemView!!.findViewById(R.id.main_HalCall) as ImageView
}
