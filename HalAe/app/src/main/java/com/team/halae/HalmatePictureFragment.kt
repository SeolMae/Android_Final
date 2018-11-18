package com.team.halae

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.fragment_halmate.*
import kotlinx.android.synthetic.main.fragment_halmate_picture.*
import retrofit2.Call
import retrofit2.Response

class HalmatePictureFragment : Fragment() {

    lateinit var pictureItems: ArrayList<HalmateBoardResult>
    lateinit var pictureAdapter: PictureAdapter
    lateinit var networkService: NetworkService
    lateinit var requestManager: RequestManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_halmate_picture,container,false)

        var recyclerView : RecyclerView = v.findViewById(R.id.halmate_picture_list)


        val args = arguments
        val index = args!!.getInt("hal_idx")
        networkService = ApplicationController.instance.networkService
        requestManager = Glide.with(this)
        recyclerView.layoutManager = GridLayoutManager(this.context, 3)

        var halmateBoardResponse  = networkService.getHalmateBoard(index.toString())
        halmateBoardResponse.enqueue(object : retrofit2.Callback<HalmateBoardResponse> {
            override fun onResponse(call: Call<HalmateBoardResponse>?, response: Response<HalmateBoardResponse>?) {
                if(response!!.isSuccessful){
                    pictureItems = response.body().result
                    pictureAdapter = PictureAdapter(pictureItems, requestManager)
                    recyclerView.adapter = pictureAdapter
                    Log.e("안녕",response.body().message)
                }
            }

            override fun onFailure(call: Call<HalmateBoardResponse>?, t: Throwable?) {
                Log.e("통신오류",t.toString())
            }

        })


        return v
    }
}