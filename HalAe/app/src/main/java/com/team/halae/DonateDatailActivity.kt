package com.team.halae

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.RequestManager

class DonateDatailActivity : AppCompatActivity() {

    private var networkService : NetworkService? = null
    lateinit var mGlideRequestManager : RequestManager

    private var donatetitle : TextView? = null
    private var halmatename : TextView? = null
    private var goalmoney : TextView? = null
    private var percentage : TextView? = null
    private var nowmoney : TextView? = null
    private var leftdays : TextView? = null

    private var progressbar : ProgressBar? = null

    private var donatebtn : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate_datail)

        var intent = getIntent()

        donatetitle = findViewById(R.id.donate_title)
        halmatename = findViewById(R.id.donate_halmate_name)
        goalmoney = findViewById(R.id.donate_goal_money)
        percentage = findViewById(R.id.donate_percent)
        nowmoney = findViewById(R.id.donate_now_money)
        leftdays = findViewById(R.id.donate_left_days)

        progressbar = findViewById(R.id.donate_progress_bar)

        donatebtn = findViewById(R.id.donate_button)

        progressbar!!.setProgress(50)


        donatetitle!!.setText(intent.getStringExtra("title"))

        var halmatenameandage : String = intent.getStringExtra("name") + "(" + intent.getStringArrayExtra("age") + ")"
        halmatename!!.setText(halmatenameandage)

        var goalmoneytemp : String = intent.getStringExtra("goalmoney") + "원"
        goalmoney!!.setText(goalmoneytemp)

        var percentagetemp : String = intent.getStringExtra("percent") + "%"
        percentage!!.setText(percentagetemp)

        //현재 금액 해야 함
        var nowmoneytemp : String = intent.getStringExtra("nowmoney") + " 원"
        nowmoney!!.setText(nowmoneytemp)

        var leftdaystemp : String = "종료 " + intent.getStringExtra("leftdays") + "일 전"
        percentage!!.setText(percentagetemp)

        //프로그래스 바 설정
        progressbar!!.setProgress(intent.getStringExtra("percent").toInt())
    }

}
