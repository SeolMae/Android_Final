package com.team.halae

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_board_write.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.halmate_items.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    var token: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6OTI3MzA0MjIyLCJpYXQiOjE1MzgyOTY3NDMsImV4cCI6MTU0MDg4ODc0M30.KuDpOGzvy4NzQJjUJ0InjdSIdWYmhn10CfCS1QLLBzA"
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkService = ApplicationController.instance!!.networkService

        //token = getIntent().getStringExtra("token")

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
                        myHalLists!!.adapter = adapter
                        main_dontHal.setVisibility(TextView.GONE)
                    }
                    else if(response!!.body().message == "user don't have halmmate"){
                        main_HalMate.setVisibility(RecyclerView.GONE)

                    }
                }
            }
        })

        val getRecommendDonationResponse = networkService!!.getRecommenDonationList(token)
        getRecommendDonationResponse.enqueue(object : Callback<RecommendDonationResponse>{
            override fun onFailure(call: Call<RecommendDonationResponse>?, t: Throwable?) {
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
                        DoStatus1.setProgress(response!!.body().data[0].don_percent)
                        don_idx = response!!.body().data[0].don_idx


                        requestManager!!.load(response!!.body().data[1].don_img).into(DoWithPic1)
                        DoWithTopic2.setText(response!!.body().data[1].don_title)
                        DoHalName2.setText(response!!.body().data[1].hal_name + " 할머니")
                        DoTotalMoney2.setText(response!!.body().data[1].don_now.toString() + "원")
                        DoPercent2.setText(response!!.body().data[1].don_percent.toString()+"%")
                        DoStatus2.setProgress(response!!.body().data[1].don_percent)
                        don_idx2 = response!!.body().data[1].don_idx

                    }
                }
            }

        })

        val getRecommendVolResponse = networkService!!.getRecommentVolList(token)
        getRecommendVolResponse.enqueue(object : Callback<RecommendVolResponse>{
            override fun onFailure(call: Call<RecommendVolResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<RecommendVolResponse>?, response: Response<RecommendVolResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message == "Successfully get recommend_donate"){
                        requestManager!!.load(response!!.body().data[0].board_img).into(recommedPic1)
                        recommedTitle1.setText(response!!.body().data[0].board_title)
                        board_idx=response!!.body().data[0].board_idx

                        requestManager!!.load(response!!.body().data[0].board_img).into(recommedPic2)
                        recommedTitle2.setText(response!!.body().data[0].board_title)
                        board_idx=response!!.body().data[1].board_idx
                    }
                }
            }

        })

        val getRecommendHalResponse = networkService!!.getRecommendHalList(token)
        getRecommendHalResponse.enqueue(object : Callback<RecommendHalResponse>{
            override fun onFailure(call: Call<RecommendHalResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<RecommendHalResponse>?, response: Response<RecommendHalResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message == "Successfully get recommend_donate"){
                        requestManager!!.load(response!!.body().data[0].hal_img).into(recoHalImg1)
                        HalAge.setText(response!!.body().data[0].hal_name + " 할머니, " + response!!.body().data[0].hal_age.toString()+"세")
                        HalAdd.setText(response!!.body().data[0].hal_add)
                        HalHob.setText("#" + response!!.body().data[0].inter_list.interest[0] + " #" + response!!.body().data[0].inter_list.interest[1])
                        hal_idx = response!!.body().data[0].hal_idx

                        requestManager!!.load(response!!.body().data[1].hal_img).into(recoHalImg2)
                        HalAge2.setText(response!!.body().data[1].hal_name + " 할머니, " + response!!.body().data[0].hal_age.toString()+"세")
                        HalAdd2.setText(response!!.body().data[1].hal_add)
                        HalHob2.setText("#" + response!!.body().data[1].inter_list.interest[0] + " #" + response!!.body().data[1].inter_list.interest[1])
                        hal_idx2 = response!!.body().data[0].hal_idx
                    }
                }
            }

        })

        recommedPic1.setOnClickListener{
            var intent = Intent(this, BoardDetailActivity::class.java)
            intent.putExtra("board_idx",board_idx)
            startActivity(intent)
        }
        recommedPic2.setOnClickListener{
            var intent = Intent(this, BoardDetailActivity::class.java)
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

