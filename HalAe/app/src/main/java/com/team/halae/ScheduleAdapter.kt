package com.team.halae

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class ScheduleAdapter( var scheduleItems : ArrayList<ScheduleItem>, var context: Context) : RecyclerView.Adapter<ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.halmate_schedule_item, parent, false)

        return ScheduleViewHolder(mainView)
    }

    override fun getItemCount(): Int = scheduleItems.size

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.scheduleName.text = scheduleItems[position].name
        holder.scheduleTime.text = scheduleItems[position].time

        holder.itemView.setOnClickListener{
            Log.e("아이템클릭",position.toString())


            val intent : Intent = Intent(context, HalmateScheduleDetailActivity::class.java)
//            intent.putExtra("profileImage",kakaoItems[position].profile)
//            intent.putExtra("chattingName",kakaoItems[position].name)
            context.startActivity(intent)
        }


    }
}