package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager

class HalAdapter (var dataList: ArrayList<UsrHalData>?, var requestManager: RequestManager) : RecyclerView.Adapter<HalViewHolder>() {
    private var onItemClick : View.OnClickListener? = null

    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: HalViewHolder, position: Int) {
        requestManager.load(dataList!!.get(position).hal_img).into(holder!!.HalImg)
        if(dataList!!.get(position).hal_gender==0){
            holder!!.HalName.text = dataList!!.get(position).hal_name + " 할아버지"
        }
        else{
            holder!!.HalName.text = dataList!!.get(position).hal_name + " 할머니"
        }
        holder!!.HalDD.text = "만난지 " + dataList!!.get(position).met_time.toString() + "일"
        //holder!!.HalMsg.setImageResource()
        //holder!!.HalCall.setImageResource(dataList!!.get(position))
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): HalViewHolder {
        val mainView : View = LayoutInflater.from(p0!!.context).inflate(R.layout.halmate_items, p0, false)
        mainView.setOnClickListener(onItemClick)
        return HalViewHolder(mainView)
    }

    fun setOnItemClickListener (l : View.OnClickListener) {
        onItemClick = l
    }

}

