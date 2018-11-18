package com.team.halae

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login1.*

class Login1Activity : AppCompatActivity() {

    var name : String? = null
    var phonenumber : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login1)

        name = login1_name.getText().toString()
        phonenumber = login1_nubmer.getText().toString()

        next_login2.setOnClickListener{

            var intent = Intent(this, LoginActivity2::class.java)
            intent.putExtra("usr_name", name)
            intent.putExtra("usr_phone", phonenumber)
            startActivity(intent)

        }

        login1_man.setOnClickListener{
            login1_man.setBackgroundColor(0x9ce3ff)
            login1_woman.setBackgroundColor(0xffffff)
        }

        login1_woman.setOnClickListener{
            login1_man.setBackgroundColor(0xffffff)
            login1_woman.setBackgroundColor(0x9ce3ff)
        }
    }
}
