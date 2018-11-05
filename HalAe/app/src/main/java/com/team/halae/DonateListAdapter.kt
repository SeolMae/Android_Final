package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager

class DonateListAdapter(var donateList : ArrayList<DonateListItem>,var glideRequest : RequestManager) : RecyclerView.Adapter<DonateListViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonateListViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.donate_list_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return DonateListViewHolder(mainView)

    }

    override fun getItemCount(): Int = donateList.size

    override fun onBindViewHolder(holder: DonateListViewHolder, position: Int) {

        //holder.donateImg.setImageResource(donateList[position].donateimg)
        glideRequest.load(donateList[position].donateimg)
        holder.donateTitle.setText(donateList[position].donatetitle)
        holder.donateHalmatename.setText(donateList[position].donatehalmatename)
        holder.donateGoalmoney.setText(donateList[position].donategoalmoney)
        holder.donatePercent.setText(donateList[position].donatepercent)
        holder.donateLeftdays.setText(donateList[position].donateleftday)
    }

    fun setOnItemClickListner(I:View.OnClickListener){
        onItemClick = I
    }

}