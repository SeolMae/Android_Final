package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BoardAdapter(var boardItems : ArrayList<BoardItem>) : RecyclerView.Adapter<BoardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.halmate_board_item, parent, false)
        return BoardViewHolder(mainView)
    }

    override fun getItemCount(): Int = boardItems.size

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.boardImage.setImageResource(boardItems[position].image)
        holder.boardTitle.text = boardItems[position].title
        holder.boardDate.text = boardItems[position].date
    }
}