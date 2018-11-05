package com.team.halae

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_halmate_select_date.*

class HalmateScheduleSelectActivity: AppCompatActivity(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v){
            schedule_select_add->{
                val intent = Intent(applicationContext, HalmateScheduleAddActivity::class.java)
                startActivity(intent)
            }
        }
    }

    lateinit var scheduleItems: ArrayList<ScheduleItem>
    lateinit var scheduleAdapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halmate_select_date)

        schedule_select_add.setOnClickListener(this)

        scheduleItems = ArrayList()
        scheduleItems.add(ScheduleItem("최고운", "오후 2:00 - 오후 5:00"))
        scheduleItems.add(ScheduleItem("이선영", "오후 2:00 - 오후 5:00"))
        scheduleAdapter = ScheduleAdapter(scheduleItems, this)
        schedule_list.layoutManager = LinearLayoutManager(this)
        schedule_list.adapter = scheduleAdapter


    }

}