package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SearchVolunteerAdapter(var volunteerList : ArrayList<SearchVolunteerItem>) : RecyclerView.Adapter<SearchVolunteerViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVolunteerViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.search_detail_volunteer_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return SearchVolunteerViewHolder(mainView)

    }

    override fun getItemCount(): Int = volunteerList.size

    override fun onBindViewHolder(holder: SearchVolunteerViewHolder, position: Int) {
        holder.volunteerImg.setImageResource(volunteerList[position].volunteerimg)
        holder.volunteerName.setText(volunteerList[position].volunteername)

    }

    fun setOnItemClickListner(I:View.OnClickListener){
        onItemClick = I
    }

    fun setOnItemClickListner() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
