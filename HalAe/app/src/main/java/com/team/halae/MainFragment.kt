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
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainFragment : Fragment(){

    var token: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6OTI3MzA0MjIyLCJpYXQiOjE1NDE2Njk4ODYsImV4cCI6MTU0NDI2MTg4Nn0.SD5FN00X_9pTl2SQ8eZov3Hg6CJ5k9VL9WwqYVnVzwA"
    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    private var myHalLists : RecyclerView? = null
    private var adapter : HalAdapter? = null
    private var myHalDatas : ArrayList<UsrHalData>? = null

    var board_idx : Int? = null
    var board_idx2 : Int? = null

    var hal_idx : Int? = null
    var hal_idx2 : Int? = null

    var don_idx : Int? = null
    var don_idx2 : Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        myHalLists!!.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        //token = getIntent().getStringExtra("token")
        Log.v("tokenbabo", token)
        token=arguments!!.getString("token")


        val getUsrHalResponse = networkService!!.getUsrHalList(token)
        getUsrHalResponse.enqueue(object : Callback<UsrHalListResponse> {
            override fun onFailure(call: Call<UsrHalListResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }
            override fun onResponse(call: Call<UsrHalListResponse>?, response: Response<UsrHalListResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message == "Successfully get usr_halmae"){
                        adapter = HalAdapter(response.body().data, requestManager!!)
                        myHalDatas = response.body().data
                        main_HalMate!!.adapter = adapter
                        main_dontHal.setVisibility(TextView.GONE)
                    }
                    else if(response!!.body().message == "user don't have halmmate"){
                        main_HalMate.setVisibility(RecyclerView.GONE)

                    }
                }
            }
        })

        val getRecommendDonationResponse = networkService!!.getRecommenDonationList(token)
        getRecommendDonationResponse.enqueue(object : Callback<RecommendDonationResponse> {
            override fun onFailure(call: Call<RecommendDonationResponse>?, t: Throwable?) {
                Log.v("lalala?","la?")
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<RecommendDonationResponse>?, response: Response<RecommendDonationResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message == "Successfully get recommend_donate"){
                        requestManager!!.load(response!!.body().data[0].don_img).into(DoWithPic1)
                        DoWithTopic1.setText(response!!.body().data[0].don_title)
                        DoHalName1.setText(response!!.body().data[0].hal_name + " 할머니")
                        DoTotalMoney1.setText(response!!.body().data[0].don_now.toString() + "원")
                        DoPercent1.setText(response!!.body().data[0].don_percent.toString()+"%")
                        DoStatus1.setProgress(response!!.body().data[0].don_percent.toInt())
                        don_idx = response!!.body().data[0].don_idx


                        requestManager!!.load(response!!.body().data[1].don_img).into(DoWithPic1)
                        DoWithTopic2.setText(response!!.body().data[1].don_title)
                        DoHalName2.setText(response!!.body().data[1].hal_name + " 할머니")
                        DoTotalMoney2.setText(response!!.body().data[1].don_now.toString() + "원")
                        DoPercent2.setText(response!!.body().data[1].don_percent.toString()+"%")
                        DoStatus2.setProgress(response!!.body().data[1].don_percent.toInt())
                        don_idx2 = response!!.body().data[1].don_idx

                    }
                }
            }

        })

        val getRecommendVolResponse = networkService!!.getRecommentVolList(token)
        getRecommendVolResponse.enqueue(object : Callback<RecommendVolResponse> {
            override fun onFailure(call: Call<RecommendVolResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
                Log.v("lalala?","la?2")
            }

            override fun onResponse(call: Call<RecommendVolResponse>?, response: Response<RecommendVolResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message == "Successfully get recommend_board"){
                        requestManager!!.load(response!!.body().data[0].board_img).into(recommedPic1)
                        recommedTitle1.setText(response!!.body().data[0].board_title)
                        board_idx=response!!.body().data[0].board_idx

                        requestManager!!.load(response!!.body().data[0].board_img).into(recommedPic2)
                        recommedTitle2.setText(response!!.body().data[0].board_title)
                        board_idx2=response!!.body().data[1].board_idx
                    }
                }
            }

        })

        val getRecommendHalResponse = networkService!!.getRecommendHalList(token)
        getRecommendHalResponse.enqueue(object : Callback<RecommendHalResponse> {
            override fun onFailure(call: Call<RecommendHalResponse>?, t: Throwable?) {
                Log.v("lalala?","la?3")
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<RecommendHalResponse>?, response: Response<RecommendHalResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message == "Successfully get recommend_halmate"){
                        requestManager!!.load(response!!.body().data[0].hal_img).into(recoHalImg1)
                        HalAge.setText(response!!.body().data[0].hal_name + " 할머니, " + response!!.body().data[0].hal_age.toString()+"세")
                        HalAdd.setText(response!!.body().data[0].hal_address)
                        HalHob.setText("#" + response!!.body().data[0].inter_list[0] + " #" + response!!.body().data[0].inter_list[1])
                        hal_idx = response!!.body().data[0].hal_idx

                        requestManager!!.load(response!!.body().data[1].hal_img).into(recoHalImg2)
                        HalAge2.setText(response!!.body().data[1].hal_name + " 할머니, " + response!!.body().data[0].hal_age.toString()+"세")
                        HalAdd2.setText(response!!.body().data[1].hal_address)
                        HalHob2.setText("#" + response!!.body().data[1].inter_list[0] + " #" + response!!.body().data[1].inter_list[1])
                        hal_idx2 = response!!.body().data[0].hal_idx
                    }
                }
            }

        })

        recommedPic1.setOnClickListener{
            var intent = Intent(activity, BoardDetailActivity::class.java)
            intent.putExtra("board_idx",board_idx)
            startActivity(intent)
        }
        recommedPic2.setOnClickListener{
            var intent = Intent(activity, BoardDetailActivity::class.java)
            intent.putExtra("board_idx",board_idx2)
            startActivity(intent)
        }

        /* 할머니 페이지로 설정!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        main_DoRec1.setOnClickListener{
            var intent = Intent(this, BoardDetailActivity::class.java)
            intent.putExtra("hal_idx",board_idx)
            startActivity(intent)
        }

        main_DoRec2.setOnClickListener{
            var intent = Intent(this, BoardDetailActivity::class.java)
            intent.putExtra("hal_idx",board_idx2)
            startActivity(intent)
        }
        */

        /* 할머니 기부페이지로 설정!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        main_HalDoRec1.setOnClickListener{
            var intent = Intent(this, BoardDetailActivity::class.java)
            intent.putExtra("hal_idx",board_idx2)
            startActivity(intent)
        }

        main_HalDoRec2.setOnClickListener{
            var intent = Intent(this, BoardDetailActivity::class.java)
            intent.putExtra("hal_idx",board_idx2)
            startActivity(intent)
        }
        */
    }

}