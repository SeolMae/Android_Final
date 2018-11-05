package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RecordAdapter (var searchRecord : ArrayList<RecordItem>) : RecyclerView.Adapter<RecordViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.search_magnifying_item, parent, false)
        return RecordViewHolder(mainView)
    }

    override fun getItemCount(): Int = searchRecord.size

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.recordword.setText(searchRecord[position].recordword)
    }


}