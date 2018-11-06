package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager

class MyDonateAdapter (var dataList: ArrayList<UserDonateRecordData>?, var requestManager: RequestManager) : RecyclerView.Adapter<MyDonateViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MyDonateViewHolder {
        val mainView : View = LayoutInflater.from(p0!!.context).inflate(R.layout.mydonate_items, p0, false)
        mainView.setOnClickListener(onItemClick)
        return MyDonateViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: MyDonateViewHolder, position: Int) {
        holder!!.donateTitle.text = dataList!!.get(position).don_title
        holder!!.donateHalmate.text = dataList!!.get(position).hal_name
        holder!!.donateAmount.text = dataList!!.get(position).don_money.toString()
        holder!!.donateDate.text = dataList!!.get(position).don_time
    }

    fun setOnItemClickListener (l : View.OnClickListener) {
        onItemClick = l
    }
}