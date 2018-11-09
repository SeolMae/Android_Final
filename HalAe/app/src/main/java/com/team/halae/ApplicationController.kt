package com.team.halae

import android.app.Activity
import android.app.Application
import android.content.Context
import android.widget.Toast
import com.kakao.auth.KakaoSDK
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationController : Application() {
    lateinit var networkService: NetworkService
        private set
    private val baseUrl = "http://13.209.101.255:3006"
    var appContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        instance = this
        globalApplicationContext = this
        KakaoSDK.init(KakaoSDKAdapter())
        buildNetwork()
    }


    fun buildNetwork() {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        networkService = retrofit.create(NetworkService::class.java)
    }

    fun makeToast(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        lateinit var instance: ApplicationController
        @Volatile
        var globalApplicationContext: ApplicationController? = null
            private set
        // Activity가 올라올때마다 Activity의 onCreate에서 호출
        @Volatile
        var currentActivity: Activity? = null
    }
}
