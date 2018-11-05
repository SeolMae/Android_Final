package com.team.halae

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_board_detail.*
import kotlinx.android.synthetic.main.post_items.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardDetailActivity : AppCompatActivity() {

    var getImage : CircleImageView? = null

    var board_idx: Int = null!!
    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)

        board_idx = getIntent().getIntExtra("board_idx", board_idx!!)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        board_bookmark.setOnClickListener{
            //board_bookmark.setImageResource()
        }

        board_comment.setOnClickListener{
            var intent = Intent(this, BoardCommentActivity::class.java)
            startActivity(intent)
        }

        val getBoardDetailResponse = networkService!!.getBoardDetail(board_idx)
        getBoardDetailResponse.enqueue(object : Callback<BoardDetailResponse>{
            override fun onFailure(call: Call<BoardDetailResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<BoardDetailResponse>?, response: Response<BoardDetailResponse>?) {
                getImage = findViewById(R.id.post_userImg) as CircleImageView
                requestManager!!.load(response!!.body().data.usr_img).into(getImage)
                post_userName.text = response!!.body().data.usr_name
                board_title.text = response!!.body().data.board_title
                post_Date.text = response!!.body().data.board_date
                requestManager!!.load(response!!.body().data.board_img).into(board_image)
                board_content.text = response!!.body().data.board_desc

                if(response!!.body().data.bookmark_flag==0){
                    //북마크 안 체크된 것
                    //requestManager!!.load(R.drawable.img_profie_basic).into(board_bookmark)
                }
                else{
                    //북마크 체크된 것
                    //requestManager!!.load(R.drawable.img_profie_basic).into(board_bookmark)
                }
            }

        })



    }
}
