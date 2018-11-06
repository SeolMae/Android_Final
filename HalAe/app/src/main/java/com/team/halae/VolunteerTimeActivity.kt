package com.team.halae

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VolunteerTimeActivity : AppCompatActivity() {

    var token: String = " "
    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    private var userVoltimeList: RecyclerView? = null

    private var adapter: VoltimeAdapter? = null
    private var userVoltimeDatas: ArrayList<UserVoltimeRecordData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer_time)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        val getUserVoltimeResponse = networkService!!.getUserVoltime(token)
        getUserVoltimeResponse.enqueue(object : Callback<UserVoltimeResponse> {
            override fun onFailure(call: Call<UserVoltimeResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<UserVoltimeResponse>?, response: Response<UserVoltimeResponse>?) {
                if (response!!.isSuccessful) {
                    if (response!!.body().message == "Successfully get user's volunteer list") {
                        adapter = VoltimeAdapter(response.body().data, requestManager!!)
                        userVoltimeDatas = response.body().data
                        userVoltimeList!!.adapter = adapter
                    }
                }
            }

        })
    }
}

