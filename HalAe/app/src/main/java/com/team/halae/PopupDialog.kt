package com.team.halae

import android.support.v7.app.AppCompatActivity
import android.os.Bundle


import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.popup_dialog.*

class PopupDialog (myContext: Context?,
                   private var content: String?, private var cancelListener: View.OnClickListener?,
                   private var okListener: View.OnClickListener?, private var okContent: String?) : Dialog(myContext, android.R.style.Theme_Translucent_NoTitleBar) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount =0.6f
        window!!.attributes = lpWindow
        setContentView(R.layout.popup_dialog)

        if(okListener !=null && cancelListener !=null){
            popup_ok.setOnClickListener(okListener)
            popup_cancel.setOnClickListener(cancelListener)
        }else{

        }

        //change_title.text = content
    }
}
