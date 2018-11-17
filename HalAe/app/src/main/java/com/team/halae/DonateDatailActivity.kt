package com.team.halae

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.team.halae.R.id.detail_background_layout

class DonateDatailActivity : AppCompatActivity() {

    private var networkService : NetworkService? = null
    lateinit var mGlideRequestManager : RequestManager

    private var donateimg : ImageView? = null
    private var donatetitle : TextView? = null
    private var halmatename : TextView? = null
    private var goalmoney : TextView? = null
    private var percentage : TextView? = null
    private var donatecontext : TextView? = null
    private var nowmoney : TextView? = null
    private var leftdays : TextView? = null

    private var startdate : TextView? = null
    private var enddate : TextView? = null
    private var goalmoney2 : TextView? = null

    private var progressbar : ProgressBar? = null

    private var donatebtn : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate_datail)

        var intent = getIntent()

        mGlideRequestManager = Glide.with(this)

        donateimg = findViewById(R.id.donate_background_img)
        donatetitle = findViewById(R.id.donate_title)
        halmatename = findViewById(R.id.donate_halmate_name)
        goalmoney = findViewById(R.id.donate_goal_money)
        percentage = findViewById(R.id.donate_percent)
        nowmoney = findViewById(R.id.donate_now_money)
        leftdays = findViewById(R.id.donate_left_days)
        donatebtn = findViewById(R.id.donate_button)
        donatecontext = findViewById(R.id.donate_detail_context)

        startdate = findViewById(R.id.donate_detail_startdate)
        enddate = findViewById(R.id.donate_detail_enddate)
        goalmoney2 = findViewById(R.id.donate_detail_goalmoney)

        progressbar = findViewById(R.id.donate_detail_progress_bar)

        Log.v("sylee", intent.toString())

        mGlideRequestManager.load(intent.getStringExtra("img")).into(donateimg)
        donatetitle!!.setText(intent.getStringExtra("title"))

        var halmatenameandage : String = intent.getStringExtra("name")
        halmatename!!.setText(halmatenameandage)
        Log.v("sylee", halmatenameandage)

        donatecontext!!.setText(intent.getStringExtra("content"))

        var goalmoneytemp : String = intent.getStringExtra("goalmoney")
        goalmoney!!.setText(goalmoneytemp)

        var percentagetemp : String = intent.getStringExtra("percent") + "%"
        percentage!!.setText(percentagetemp)

        //현재 금액 해야 함
        var nowmoneytemp : String = intent.getStringExtra("nowmoney")
        nowmoney!!.setText(nowmoneytemp)

        var leftdaystemp : String = "종료 " + intent.getStringExtra("leftdays") + "일 전"
        percentage!!.setText(percentagetemp)

        //donatecontext!!.setText(intent.getStringExtra(""))
        //프로그래스 바 설정
        var percentrange = IntRange(0, intent.getStringExtra("percent").length-2)
        var percentnumstr = intent.getStringExtra("percent").slice(percentrange)
        Log.v("sylee", percentnumstr)
        progressbar!!.setProgress(percentnumstr.toInt())

        startdate!!.setText("모금 시작 날짜 : " + intent.getStringExtra("startdate"))
        enddate!!.setText("모금 종료 날짜 : " + intent.getStringExtra("enddaate"))
        goalmoney2!!.setText(intent.getStringExtra("goalmoney"))

    }

}
