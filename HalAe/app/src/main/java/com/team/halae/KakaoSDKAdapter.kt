package com.team.halae

import android.app.Activity
import android.content.Context
import com.kakao.auth.*

class KakaoSDKAdapter : KakaoAdapter() {
    /**
     * Session Config에 대해서는 default값들이 존재한다.
     * 필요한 상황에서만 override해서 사용하면 됨.
     * @return Session의 설정값.
     */
    override fun getSessionConfig(): ISessionConfig {
        return object : ISessionConfig {
            override fun getAuthTypes(): Array<AuthType> {
                return arrayOf(AuthType.KAKAO_ACCOUNT)
            }

            override fun isUsingWebviewTimer(): Boolean {
                return false
            }

            override fun isSecureMode(): Boolean {
                return false
            }


            override fun getApprovalType(): ApprovalType {
                return ApprovalType.INDIVIDUAL
            }

            override fun isSaveFormData(): Boolean {
                return true
            }
        }
    }


    override fun getApplicationConfig(): IApplicationConfig {
        return object : IApplicationConfig {
            internal val topActivity: Activity
                get() = ApplicationController.currentActivity!!

            override fun getApplicationContext(): Context {
                return ApplicationController.globalApplicationContext!!
            }
        }
    }


}