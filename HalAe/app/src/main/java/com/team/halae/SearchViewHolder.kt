package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class SearchViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)  {
    var halmateImg : ImageView = itemView!!.findViewById(R.id.halmate_search_img) as ImageView
    var halmateName : TextView = itemView!!.findViewById(R.id.halmate_search_name) as TextView
    var halmateAge : TextView = itemView!!.findViewById(R.id.halmate_search_age) as TextView
    var halmateAddress : TextView = itemView!!.findViewById(R.id.halmate_search_address) as TextView
    var hamateInterest : TextView = itemView!!.findViewById(R.id.halmate_search_interest) as TextView


}