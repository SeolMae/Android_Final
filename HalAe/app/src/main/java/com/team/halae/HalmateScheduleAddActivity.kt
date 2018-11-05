package com.team.halae

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HalmateScheduleAddActivity: AppCompatActivity(){

    lateinit var networkService: NetworkService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halmate_schedule_add)

        networkService = ApplicationController.instance.networkService

        val arrayList = ArrayList<Int>()
        arrayList.add(1)
        arrayList.add(2)
        val postUserSchedule = PostUserSchedule("2018-08-15",1,"안녕",15,17,arrayList)
        val postChoiceResponse = networkService.postUserSchedule("abce",postUserSchedule)
        postChoiceResponse.enqueue(object : Callback<PostUserScheduleResponse> {
            override fun onFailure(call: Call<PostUserScheduleResponse>?, t: Throwable?) {
                Log.v("text", t.toString())
            }

            override fun onResponse(call: Call<PostUserScheduleResponse>?, response: Response<PostUserScheduleResponse>?) {


                if(response!!.isSuccessful){
                    Log.e("check", response.body().message)

                }
            }
        })
    }

}