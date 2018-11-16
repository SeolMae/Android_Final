package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager

class BoardAdapter(var boardItems : ArrayList<HalmateBoardResult>, var requestManager: RequestManager) : RecyclerView.Adapter<BoardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.halmate_board_item, parent, false)
        return BoardViewHolder(mainView)
    }

    override fun getItemCount(): Int = boardItems.size

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        requestManager.load(boardItems[position].board_img).into(holder.boardImage)
        holder.boardTitle.text = boardItems[position].board_title
        holder.boardDate.text = boardItems[position].board_time
    }
}