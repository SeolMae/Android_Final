package com.team.halae

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import java.math.BigInteger

class DonateListAdapter(var donateList : ArrayList<DonateListItem>,var glideRequest : RequestManager) : RecyclerView.Adapter<DonateListViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonateListViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.donate_list_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return DonateListViewHolder(mainView)

    }

    override fun getItemCount(): Int = donateList.size

    override fun onBindViewHolder(holder: DonateListViewHolder, position: Int) {

        var donateViewHolder = holder as DonateListViewHolder

        var nowmoneyrange = IntRange(0, donateList[position].donatenowmoney.length-3)
        var goalmoneyrange = IntRange(11, donateList[position].donategoalmoney.length-2)

        Log.v("sysy", donateList[position].donatenowmoney.slice(nowmoneyrange))
        Log.v("sysy", donateList[position].donategoalmoney.slice(goalmoneyrange))

        var nowmoneynum  : Float = donateList[position].donatenowmoney.slice(nowmoneyrange).toFloat()
        var goalmoneynum : Float = donateList[position].donategoalmoney.slice(goalmoneyrange).toFloat()
        var progressnum : Int = ((nowmoneynum/goalmoneynum) * 100).toInt()

        Log.v("sysy", progressnum.toString())

        //holder.donateImg.setImageResource(donateList[position].donateimg)
        glideRequest.load(donateList[position].donateimg).into(donateViewHolder.donateImg)
        holder.donateTitle.setText(donateList[position].donatetitle)
        holder.donateHalmatename.setText(donateList[position].donatehalmatename)
        holder.donateGoalmoney.setText(donateList[position].donategoalmoney)
        holder.danateProgressBar.setProgress(progressnum)
        holder.donatePercent.setText(donateList[position].donatepercent)
        holder.donateLeftdays.setText(donateList[position].donateleftday)


    }

    fun setOnItemClickListner(I:View.OnClickListener){
        onItemClick = I
    }

}