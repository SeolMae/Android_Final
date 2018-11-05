package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class RecordViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView!!){
    var recordword : TextView = itemView!!.findViewById(R.id.search_word) as TextView

}