package com.team.halae

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_board.*
import kotlinx.android.synthetic.main.activity_my_donate.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class BoardActivity : AppCompatActivity(), View.OnClickListener {

    var searchBtn : ImageButton? = null
    var boardList : RecyclerView? = null


    var token: String = " "
    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null


    private var boardLists : RecyclerView? = null
    private var adapter : PostAdapter? = null
    private var boardDatas : ArrayList<BoardData>? = null

    override fun onClick(v: View?) {

    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)


        searchBtn = findViewById(R.id.board_searchBtn) as ImageButton
        boardList = findViewById(R.id.board_list) as RecyclerView
        boardList!!.layoutManager = LinearLayoutManager(this)

        board_addPost.setOnClickListener{
            var intent = Intent(this, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        val getBoardListResponse = networkService!!.getBoardList(token)
        getBoardListResponse.enqueue(object : Callback<BoardListResponse>{
            override fun onFailure(call: Call<BoardListResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<BoardListResponse>?, response: Response<BoardListResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message == "Successfully get board list"){
                        adapter = PostAdapter(response.body().data, requestManager!!)
                        boardDatas = response.body().data
                        boardLists!!.adapter = adapter
                    }
                }
            }

        })
    }
}
