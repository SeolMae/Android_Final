package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class PostViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var userImg: ImageView = itemView!!.findViewById(R.id.post_list_userImg) as ImageView
    var userName : TextView = itemView!!.findViewById(R.id.post_list_userName) as TextView
    var postDate : TextView = itemView!!.findViewById(R.id.post_list_Date) as TextView
    var postName : TextView = itemView!!.findViewById(R.id.post_list_postName) as TextView
    var postContent : TextView = itemView!!.findViewById(R.id.post_list_postContent) as TextView
    var postImg : ImageView = itemView!!.findViewById(R.id.post_list_postImg) as ImageView
}