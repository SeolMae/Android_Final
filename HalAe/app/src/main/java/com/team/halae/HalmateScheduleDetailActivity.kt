package com.team.halae

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_halmate_schedule_detail.*

class HalmateScheduleDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halmate_schedule_detail)

        var usrName = intent.getStringExtra("userName")
        var startTime = intent.getStringExtra("startTime")
        var endTime = intent.getStringExtra("endTime")

        schedule_usr_name.text = usrName
        schedule_starttime.text = startTime + "시"
        schedule_endtime.text = endTime + "시"
    }

}