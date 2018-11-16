package com.team.halae

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mypage.*


class MypageFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v){
            mypage_settingBtn->{
                var intent = Intent(activity, SettingActivity::class.java)
                startActivity(intent)
            }
            mypage_BolCerti->{
                floatDialog()
            }
            mypage_BolTime->{
                var intent = Intent(activity, VolunteerTimeActivity::class.java)
                startActivity(intent)
            }
            mypage_myDonate->{
                var intent = Intent(activity, MyDonateActivity::class.java)
                startActivity(intent)
            }
            mypage_myActivity->{
                var intent = Intent (activity, MyWorkActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private var popupDialog : PopupDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypage, container, false)

    }


    fun floatDialog(){
        popupDialog = PopupDialog(this.context, "봉사 인증하기", cancelListener = cancelListener, okListener = okListener, okContent = "확인")
        popupDialog!!.show()
    }

    private var okListener : View.OnClickListener? = View.OnClickListener {
        var edit1 = popupDialog!!.findViewById(R.id.num_1) as EditText
        var edit2 = popupDialog!!.findViewById(R.id.num_2) as EditText
        var edit3 = popupDialog!!.findViewById(R.id.num_3) as EditText
        var edit4 = popupDialog!!.findViewById(R.id.num_4) as EditText

        if(edit1==null || edit2==null || edit3==null || edit4==null){

        }
        else{
            popupDialog!!.dismiss()
        }
    }

    private var cancelListener : View.OnClickListener? = View.OnClickListener {
        popupDialog!!.dismiss()
    }
}
