package com.team.halae

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.RequestManager
import com.kakao.auth.AuthType
import com.kakao.auth.ErrorCode
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var callback : SessionCallback

    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        networkService = ApplicationController.instance!!.networkService

        callback = SessionCallback()
        Session.getCurrentSession().addCallback(callback)

        /*
        fun onClick(view:View) {
            kakao_login.performClick()
            //kakao_login.setOnClickListener{
            //kakao_login.performClick()
            callback = SessionCallback()
            Session.getCurrentSession().addCallback(callback)
            //Session.open(AuthType.KAKAO_LOGIN_ALL, this)


            val postLogin = networkService!!.postLogin(LoginPost(Session.getCurrentSession().accessToken))
            Log.v("kakaoplease", "string")
            postLogin.enqueue(object : Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                    ApplicationController.instance!!.makeToast("통신 확인")
                }

                override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                    if(response!!.body().message == "success"){
                        ApplicationController.instance!!.makeToast("로그인 성공")
                        if(response!!.body().data.flag == 0){
                            var intent = Intent(this@LoginActivity, Login1Activity::class.java)
                            startActivity(intent)
                        }
                        else if(response!!.body().data.flag == 1){
                            var intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }

            })


        //}
        }

*/
    }

    override fun onClick(v: View?) {
        kakao_login.performClick()
    }
    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    */

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    inner class SessionCallback : ISessionCallback {

        override fun onSessionOpened() {
            redirectSignupActivity()  // 세션 연결성공 시 redirectSignupActivity() 호출
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            if (exception != null) {
            }
            setContentView(R.layout.activity_login) // 세션 연결이 실패했을때
        }                                            // 로그인화면을 다시 불러옴
    }

    protected fun redirectSignupActivity() {

        //세션 연결 성공 시 SignupActivity로 넘김
        val intent = Intent(this, KakaoSignupActivity::class.java)
        // intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent)
        finish()
    }
}

