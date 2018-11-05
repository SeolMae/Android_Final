package com.team.halae

import android.graphics.Picture
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PictureAdapter(var pictureItem : ArrayList<Int>) : RecyclerView.Adapter<PictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.halmate_picture_item, parent, false)
        return PictureViewHolder(mainView)
    }

    override fun getItemCount(): Int = pictureItem.size

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.pictureImage.setImageResource(pictureItem[position])

    }
}