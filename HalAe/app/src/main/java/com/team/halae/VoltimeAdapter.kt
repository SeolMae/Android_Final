package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager

class VoltimeAdapter(var dataList: ArrayList<UserVoltimeRecordData>?, var requestManager: RequestManager) : RecyclerView.Adapter<VoltimeViewHolder>() {
    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): VoltimeViewHolder {
        val mainView : View = LayoutInflater.from(p0!!.context).inflate(R.layout.voltime_items, p0, false)
        mainView.setOnClickListener(onItemClick)
        return VoltimeViewHolder(mainView)

    }

    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: VoltimeViewHolder, position: Int) {
        holder!!.volDate.text=dataList!!.get(position).vol_date
        holder!!.volHalmate.text=dataList!!.get(position).hal_name
        holder!!.volAddtime.text=dataList!!.get(position).vol_time.toString()
        holder!!.volTotaltime.text=dataList!!.get(position).vol_sum.toString()
    }

    fun setOnItemClickListener(l : View.OnClickListener){
        onItemClick = l
    }
}
