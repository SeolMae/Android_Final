package com.team.halae

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mypage.*


class MypageActivity : AppCompatActivity() {

    var volCertiButton : RelativeLayout? =null

    private var popupDialog : PopupDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        volCertiButton = findViewById(R.id.mypage_BolCerti) as RelativeLayout
        volCertiButton!!.setOnClickListener{
            floatDialog()
        }

        mypage_settingBtn.setOnClickListener{
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        mypage_BolTime.setOnClickListener{
            var intent = Intent(this, VolunteerTimeActivity::class.java)
            startActivity(intent)
        }

        mypage_myDonate.setOnClickListener{
            var intent = Intent(this, MyDonateActivity::class.java)
            startActivity(intent)
        }

        mypage_myActivity.setOnClickListener{
            var intent = Intent (this, MyWorkActivity::class.java)
            startActivity(intent)
        }

    }



    fun floatDialog(){
        popupDialog = PopupDialog(this, "봉사 인증하기", cancelListener = cancelListener, okListener = okListener, okContent = "확인")
        popupDialog!!.show()
    }

    private var okListener : View.OnClickListener? = View.OnClickListener {
        var edit1 = popupDialog!!.findViewById(R.id.num_1) as EditText
        var edit2 = popupDialog!!.findViewById(R.id.num_2) as EditText
        var edit3 = popupDialog!!.findViewById(R.id.num_3) as EditText
        var edit4 = popupDialog!!.findViewById(R.id.num_4) as EditText

        if(edit1==null || edit2==null || edit3==null || edit4==null){
            Toast.makeText(applicationContext, "인증 번호를 입력하세요", Toast.LENGTH_LONG).show()
        }
        else{
            popupDialog!!.dismiss()
        }
    }

    private var cancelListener : View.OnClickListener? = View.OnClickListener {
        popupDialog!!.dismiss()
    }

}
