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
import kotlinx.android.synthetic.main.fragment_halmate_board.*
import retrofit2.Call
import retrofit2.Response

class HalmateBoardFragment : Fragment() {

    lateinit var boardItems: ArrayList<HalmateBoardResult>
    lateinit var boardAdapter: BoardAdapter
    lateinit var networkService: NetworkService
    lateinit var requestManager: RequestManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_halmate_board,container,false)
//
//        boardItems = ArrayList()
//        boardItems.add(BoardItem(R.drawable.sample, "제목입니다1","날짜입니다1"))
//        boardItems.add(BoardItem(R.drawable.sample, "제목입니다2","날짜입니다2"))
//        boardItems.add(BoardItem(R.drawable.sample, "제목입니다3","날짜입니다3"))

        var index = 1
        val args = arguments
        val index = args!!.getInt("hal_idx")
        var recyclerView : RecyclerView = v.findViewById(R.id.halmate_board_list)
        networkService = ApplicationController.instance.networkService
        requestManager = Glide.with(this)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        var halmateBoardResponse  = networkService.getHalmateBoard(index.toString())
        halmateBoardResponse.enqueue(object : retrofit2.Callback<HalmateBoardResponse> {
            override fun onResponse(call: Call<HalmateBoardResponse>?, response: Response<HalmateBoardResponse>?) {
                if(response!!.isSuccessful){
                    boardItems = response.body().result
                    boardAdapter = BoardAdapter(boardItems, requestManager)
                    recyclerView.adapter = boardAdapter
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