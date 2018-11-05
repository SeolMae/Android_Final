package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class PictureViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var pictureImage : ImageView = itemView!!.findViewById(R.id.halmate_picture_image) as ImageView

}