package com.team.halae

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_board.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BoardFragment : Fragment(), View.OnClickListener {
    var searchBtn : ImageButton? = null
    var boardList : RecyclerView? = null


    var token: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6OTI3MzA0MjIyLCJpYXQiOjE1NDE2Njk4ODYsImV4cCI6MTU0NDI2MTg4Nn0.SD5FN00X_9pTl2SQ8eZov3Hg6CJ5k9VL9WwqYVnVzwA"
    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null


    private var boardLists : RecyclerView? = null
    private var adapter : PostAdapter? = null
    private var boardDatas : ArrayList<BoardData>? = null
    private var board_addPost : ImageButton? =null


    override fun onClick(v: View?) {


    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {

        val v= inflater.inflate(R.layout.fragment_board, container, false)


        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        searchBtn = v.findViewById(R.id.board_searchBtn) as ImageButton
        boardLists = v.findViewById(R.id.board_list) as RecyclerView
        board_addPost =v.findViewById(R.id.board_addPost) as ImageButton

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        boardLists!!.layoutManager = LinearLayoutManager(this.context)


        board_addPost!!.setOnClickListener{
            var intent = Intent(this.context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        val getBoardListResponse = networkService!!.getBoardList(token)
        getBoardListResponse.enqueue(object : Callback<BoardListResponse> {
            override fun onFailure(call: Call<BoardListResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
                Log.v("board","nani?")
            }

            override fun onResponse(call: Call<BoardListResponse>?, response: Response<BoardListResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message == "Successfully get board list"){
                        Log.v("board","nine?")
                        if(response.body().data == null){
                            Log.v("board", "mang")
                        }
                        adapter = PostAdapter(response.body().data, requestManager!!)
                        boardDatas = response.body().data
                        boardLists!!.adapter = adapter
                    }
                }
            }

        })
    }


}
