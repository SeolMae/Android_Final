package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class AddressViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var address : TextView = itemView!!.findViewById(R.id.address_text) as TextView
}