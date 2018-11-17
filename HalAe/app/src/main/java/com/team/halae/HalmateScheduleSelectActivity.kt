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
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_halmate_select_date.*
import retrofit2.Call
import retrofit2.Response

class HalmateScheduleSelectActivity: AppCompatActivity(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v){
            schedule_select_add->{
                val intent = Intent(applicationContext, HalmateScheduleAddActivity::class.java)
                startActivity(intent)
            }
        }
    }

    lateinit var scheduleItems: ArrayList<HalmateScheduleDataSCH>
    lateinit var scheduleAdapter: ScheduleAdapter
    lateinit var networkService: NetworkService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halmate_select_date)

        schedule_select_add.setOnClickListener(this)

        var index = 1
//        var recyclerView : RecyclerView = v.findViewById(R.id.halmate_board_list)
        networkService = ApplicationController.instance.networkService
        schedule_list.layoutManager = LinearLayoutManager(this)
        var halmateScheduleResponse  = networkService.getHalmateSchedule(index.toString())
        halmateScheduleResponse.enqueue(object : retrofit2.Callback<HalmateScheuleInfoResponse> {
            override fun onResponse(call: Call<HalmateScheuleInfoResponse>?, response: Response<HalmateScheuleInfoResponse>?) {
                if(response!!.isSuccessful){
                    var selectDay = intent.getStringExtra("selectDay")
                    var selectMonth = intent.getStringExtra("selectMonth")
                    if(selectDay == "empty"){
                        Log.e("스케쥴업써!","!!")
                    }
                    else{
                        scheduleItems = response.body().data[selectMonth.toInt()].mon_sch[selectDay.toInt()].sch
                        scheduleAdapter = ScheduleAdapter(scheduleItems,this@HalmateScheduleSelectActivity)
                        schedule_list.adapter = scheduleAdapter
                    }

                    Log.e("안녕",response.body().message)
                }
            }

            override fun onFailure(call: Call<HalmateScheuleInfoResponse>?, t: Throwable?) {
                Log.e("통신오류",t.toString())
            }

        })




    }

}