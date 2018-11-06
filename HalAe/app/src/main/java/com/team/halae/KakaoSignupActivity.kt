package com.team.halae

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kakao.auth.Session
import com.kakao.auth.helper.StartActivityWrapper
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import okhttp3.internal.framed.ErrorCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class KakaoSignupActivity : Activity() {
    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */


    private var networkService: NetworkService? = null
    var token : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestMe()

        networkService = ApplicationController.instance!!.networkService
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected fun requestMe() { //유저의 정보를 받아오는 함수
        UserManagement.getInstance().requestMe(object : MeResponseCallback() {
            override fun onFailure(errorResult: ErrorResult?) {
                Log.v("kakaosignup","fail")
                val message = "failed to get user info. msg=" + errorResult!!

                Log.v("kakaosignup",errorResult.errorCode.toString())
                //val result = ErrorCode.valueOf(errorResult.errorCode.toString())

                /*
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish()
                } else {
                    redirectLoginActivity()
                }
                */
                redirectLoginActivity()

            }

            override fun onSessionClosed(errorResult: ErrorResult) {
                Log.v("kakaosignup","success")
                redirectLoginActivity()
            }

            override fun onNotSignedUp() {} // 카카오톡 회원이 아닐 시 showSignup(); 호출해야함

            override fun onSuccess(userProfile: UserProfile) {  //성공 시 userProfile 형태로 반환



                Log.v("kakaokakao",Session.getCurrentSession().accessToken)

                val postLogin = networkService!!.postLogin(LoginPost(Session.getCurrentSession().accessToken))
                postLogin.enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                        ApplicationController.instance!!.makeToast("통신 확인")
                    }

                    override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                        if(response!!.body().message == "success"){
                            ApplicationController.instance!!.makeToast("로그인 성공")
                            token = response!!.body().data.token
                        }
                    }

                })

                val kakaoID = userProfile.id.toString() // userProfile에서 ID값을 가져옴
                val kakaoNickname = userProfile.nickname     // Nickname 값을 가져옴
                redirectMainActivity() // 로그인 성공시 MainActivity로

            }
        })
    }

    private fun redirectMainActivity() {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
        //startActivity(Intent(this, MainActivity::class.java))

        finish()
    }

    protected fun redirectLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }

}