package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class BoardViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var boardImage : ImageView = itemView!!.findViewById(R.id.halmate_board_image) as ImageView
    var boardTitle : TextView = itemView!!.findViewById(R.id.halmate_board_title) as TextView
    var boardDate : TextView = itemView!!.findViewById(R.id.halmate_board_date) as TextView
}