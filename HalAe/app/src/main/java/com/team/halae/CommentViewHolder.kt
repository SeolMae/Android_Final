package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class CommentViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var commentName : TextView = itemView!!.findViewById(R.id.comment_list_name) as TextView
    var commentContent : TextView = itemView!!.findViewById(R.id.comment_list_content) as TextView
}