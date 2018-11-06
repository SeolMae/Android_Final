package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager

class MywriteAdapter(var dataList: ArrayList<MyBoardData>?, var requestManager: RequestManager) : RecyclerView.Adapter<MywriteViewHolder>()  {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MywriteViewHolder {
        val mainView : View = LayoutInflater.from(p0!!.context).inflate(R.layout.mywrite_items, p0, false)
        mainView.setOnClickListener(onItemClick)
        return MywriteViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: MywriteViewHolder, position: Int) {
        requestManager.load(dataList!!.get(position).board_img).into(holder!!.mywriteImg)
        holder!!.mywriteTitle.text=dataList!!.get(position).board_title
        holder!!.mywriteHalmate.text=dataList!!.get(position).hal_name
    }

    fun setOnItemClickListener (l : View.OnClickListener) {
        onItemClick = l
    }

}