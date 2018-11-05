package com.team.halae

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.R.attr.onClick
import com.bumptech.glide.RequestManager


class SearchAdapter(var searchResult : ArrayList<SearchItem>, var glideRequest : RequestManager) : RecyclerView.Adapter<SearchViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.halmate_search_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return SearchViewHolder(mainView)
    }

    override fun getItemCount(): Int = searchResult.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        //holder.halmateImg.setImageResource(searchResult[position].image)
        glideRequest.load(searchResult[position].image)
        holder.halmateName.setText(searchResult[position].name)
        holder.halmateAge.setText(searchResult[position].age.toString())
        holder.halmateAddress.setText(searchResult[position].address)
        holder.hamateInterest.setText(searchResult[position].interest)


    }
    fun setOnItemClickListner(I:View.OnClickListener){
        onItemClick = I

    }

}