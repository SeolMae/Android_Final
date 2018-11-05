package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SearchMagnifyingAdpater(var searchingwordList : ArrayList<SearchMagnifyingItem>) : RecyclerView.Adapter<SearchMagnifyingViewHolder>() {
    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMagnifyingViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.search_magnifying_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return SearchMagnifyingViewHolder(mainView)

    }
    override fun getItemCount(): Int = searchingwordList.size


    override fun onBindViewHolder(holder: SearchMagnifyingViewHolder, position: Int) {
        holder.searchingWord.setText(searchingwordList[position].searchingword)
        holder.magnigfyingImg.setImageResource(searchingwordList[position].magnigyingimg)

    }

    fun setOnItemClickListner(I:View.OnClickListener){
        onItemClick = I
    }

    fun setOnItemClickListner() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}