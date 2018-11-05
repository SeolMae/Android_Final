package com.team.halae

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_login_search1.*
import kotlinx.android.synthetic.main.fragment_login2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginSearchActivity1: AppCompatActivity(), View.OnClickListener{

    lateinit var addressItem: ArrayList<String>
    lateinit var addressAdapter : AddressAdapter
    override fun onClick(v: View?) {


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_search1)

        var addressList = arrayListOf("dd")
        lateinit var networkService2 : NetworkService2
        val builder = Retrofit.Builder()
        val retrofit = builder.baseUrl("http://www.juso.go.kr").addConverterFactory(GsonConverterFactory.create()).build()
        networkService2 = retrofit.create((NetworkService2::class.java))

        val address_search_keyword = intent.getStringExtra("address_keyword")
        login_address_keyword2.setText(address_search_keyword)
        val map = HashMap<String, String>()
        map.put("confmKey", "U01TX0FVVEgyMDE4MDkyOTEzNDcwOTEwODIwNDQ=")
        map.put("currentPage", "1")
        map.put("countPerPage", "100")
        map.put("keyword", address_search_keyword)
        map.put("resultType", "json")

        val postSearchLocationResponse = networkService2.postSearchLocation(map)
        postSearchLocationResponse.enqueue(object : Callback<PostSearchLocationResponse> {

            override fun onFailure(call: Call<PostSearchLocationResponse>?, t: Throwable?) {
                Log.e("failMessage",call.toString())
                Log.e("failMessage",t.toString())
            }

            override fun onResponse(call: Call<PostSearchLocationResponse>?, response: Response<PostSearchLocationResponse>?) {
                if(response!!.isSuccessful){
                    Log.e("Success!",response.body().toString())
                    var count : Int = response.body().results.juso.size
                    addressList.clear()
                    for(i in 0 until count){
                        addressList.add(i, response.body().results.juso[i].roadAddrPart1)
                    }

                    var recyclerView : RecyclerView = findViewById(R.id.address_list)
                    recyclerView.layoutManager = LinearLayoutManager(this@LoginSearchActivity1)
                    addressItem = addressList
                    addressAdapter = AddressAdapter(addressItem, this@LoginSearchActivity1)
//                    addressAdapter.setOnItemClick(this)
                    recyclerView.adapter = addressAdapter
                }
            }

        })

    }


}
