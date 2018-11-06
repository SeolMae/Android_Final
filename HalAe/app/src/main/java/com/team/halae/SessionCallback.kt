package com.team.halae

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.kakao.auth.ErrorCode
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


internal class SessionCallback: ISessionCallback {

    override fun onSessionOpened() {
        UserManagement.getInstance().requestMe(object: MeResponseCallback() {
            override fun onFailure(errorResult: ErrorResult) {
                val message = "failed to get user info. msg=" + errorResult
                val result = ErrorCode.valueOf(errorResult.getErrorCode())
                if (result === ErrorCode.CLIENT_ERROR_CODE)
                {
                    //에러로 인한 로그인 실패
                    // finish();
                }
                else
                {
                    //redirectMainActivity();
                }
            }
            override fun onSessionClosed(errorResult: ErrorResult) {}
            override fun onNotSignedUp() {
            }
            override fun onSuccess(userProfile: UserProfile) {

                //로그인에 성공하면 로그인한 사용자의 일련번호, 닉네임, 이미지url등을 리턴
                //사용자 ID는 보안상의 문제로 제공하지 않고 일련번호는 제공
                // Log.e("UserProfile", userProfile.toString());
                // Log.e("UserProfile", userProfile.getId() + "");
                val number = userProfile.getId()


            }
        })
    }
    override fun onSessionOpenFailed(exception: KakaoException) {
        // 세션 연결이 실패했을때
        // 어쩔때 실패되는지?
    }
}

/*
class SessionCallback:ISessionCallback {

    // 로그인에 성공한 상태
    override fun onSessionOpened() {
        requestMe()
    }
    // 로그인에 실패한 상태
    override fun onSessionOpenFailed(exception:KakaoException) {
        Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.errorType)
    }


    // 사용자 정보 요청
    fun requestMe() {
        // 사용자정보 요청 결과에 대한 Callback
        UserManagement.getInstance().requestMe(object:MeResponseCallback() {
            // 세션 오픈 실패. 세션이 삭제된 경우,
            override fun onSessionClosed(errorResult:ErrorResult) {
                Log.e("SessionCallback :: ", "onSessionClosed : " + errorResult.getErrorMessage())
            }
            // 회원이 아닌 경우,
            override fun onNotSignedUp() {
                Log.e("SessionCallback :: ", "onNotSignedUp")
            }
            // 사용자정보 요청에 성공한 경우,
            override fun onSuccess(userProfile:UserProfile) {
                Log.e("SessionCallback :: ", "onSuccess")
                val nickname = userProfile.getNickname()
                val email = userProfile.getEmail()
                val profileImagePath = userProfile.getProfileImagePath()
                val thumnailPath = userProfile.getThumbnailImagePath()
                val UUID = userProfile.getUUID()
                val id = userProfile.getId()
                Log.e("Profile : ", nickname + "")
                Log.e("Profile : ", email + "")
                Log.e("Profile : ", profileImagePath + "")
                Log.e("Profile : ", thumnailPath + "")
                Log.e("Profile : ", UUID + "")
                Log.e("Profile : ", id.toString() + "")
            }
            // 사용자 정보 요청 실패
            override fun onFailure(errorResult:ErrorResult) {
                Log.e("SessionCallback :: ", "onFailure : " + errorResult.getErrorMessage())
            }
        })
    }

}
*/