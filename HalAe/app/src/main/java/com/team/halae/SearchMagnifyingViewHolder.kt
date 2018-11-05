package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class SearchMagnifyingViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var magnigfyingImg : ImageView = itemView!!.findViewById(R.id.magnifying_img)
    var searchingWord : TextView = itemView!!.findViewById(R.id.search_word)
}