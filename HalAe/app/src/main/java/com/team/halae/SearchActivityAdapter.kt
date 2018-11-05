package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SearchActivityAdapter(var activityList : ArrayList<SearchActivityItem>) : RecyclerView.Adapter<SearchActivityViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchActivityViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.search_detail_activity_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return SearchActivityViewHolder(mainView)

    }

    override fun getItemCount(): Int = activityList.size

    override fun onBindViewHolder(holder: SearchActivityViewHolder, position: Int) {
        holder.activityImg.setImageResource(activityList[position].activityimg)
        holder.activityTitle.setText(activityList[position].activitytitle)

    }

    fun setOnItemClickListner(I:View.OnClickListener){
        onItemClick = I
    }

    fun setOnItemClickListner() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}