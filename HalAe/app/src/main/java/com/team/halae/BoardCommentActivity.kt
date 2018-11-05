package com.team.halae

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_board_comment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardCommentActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

    }

    var board_idx : String = " "
    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null


    private var commentList : RecyclerView? = null
    private var commentDatas : ArrayList<CommentData>? = null
    private var adapter : CommentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_comment)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        val getCommentListResponse = networkService!!.getCommentList(board_idx)
        getCommentListResponse.enqueue(object : Callback<CommentListResponse>{
            override fun onFailure(call: Call<CommentListResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<CommentListResponse>?, response: Response<CommentListResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message == "Successfully get comment list"){
                        adapter = CommentAdapter(response.body().data, requestManager!!)
                        commentDatas = response.body().data
                        commentList!!.adapter = adapter
                    }
                }
            }

        })

        boardcomment_addCo.setOnClickListener{
            var text = boardcomment_text.text.toString()
            if(text == null){

            }
            else{
                val postCommentAddResponse = networkService!!.postCommentAdd(board_idx, commentAddPost(board_idx.toInt(),text))
                postCommentAddResponse.enqueue(object : Callback<postCommentAddResponse>{
                    override fun onFailure(call: Call<postCommentAddResponse>?, t: Throwable?) {
                        ApplicationController.instance!!.makeToast("통신 확인")
                    }

                    override fun onResponse(call: Call<postCommentAddResponse>?, response: Response<postCommentAddResponse>?) {
                        if(response!!.isSuccessful){
                            if(response!!.body().message == "Successfullyr register comment"){
                                ApplicationController.instance!!.makeToast("수정 완료")
                            }
                        }
                    }

                })
            }
        }

    }


}