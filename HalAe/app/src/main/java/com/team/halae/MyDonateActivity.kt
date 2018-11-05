package com.team.halae

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_my_donate.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyDonateActivity : AppCompatActivity() {

    var token: String = " "
    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    private var userDonateList : RecyclerView? = null
    private var adapter : MyDonateAdapter? = null
    private var userDonateDatas : ArrayList<UserDonateRecordData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_donate)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        val getUserDonateResponse = networkService!!.getUserDonate(token)
        getUserDonateResponse.enqueue(object : Callback<UserDonateResponse>{
            override fun onFailure(call: Call<UserDonateResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<UserDonateResponse>?, response: Response<UserDonateResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message == "Successfully get usr_donate"){
                        mydonate_total.text = response!!.body().money_total.toString()

                        adapter = MyDonateAdapter(response.body().data, requestManager!!)
                        userDonateDatas = response.body().data
                        userDonateList!!.adapter = adapter
                    }
                }
            }

        })

    }
}