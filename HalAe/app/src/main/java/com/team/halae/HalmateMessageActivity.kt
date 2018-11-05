package com.team.halae

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.team.halae.R.id.message
import kotlinx.android.synthetic.main.activity_halmate_message.*
import kotlinx.android.synthetic.main.activity_halmate_select_date.*
import kotlinx.android.synthetic.main.my_message_item.*
import kotlinx.android.synthetic.main.their_message_item.*
import java.text.SimpleDateFormat
import java.util.*


class HalmateMessageActivity : AppCompatActivity(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v){
            message_send->{
                if(message_edit.text.toString().equals("")||message_edit.text.toString().isEmpty()|| message_edit.text.toString() == null){
                    Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
                }
                else{
                    val c : Calendar = Calendar.getInstance()
                    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val currentTime : String = df.format(c.time)

                    val database = FirebaseDatabase.getInstance()
                    val myRef = database.getReference("chats").child(currentTime)

                    val chat : Hashtable<String, String> = Hashtable()
                    chat.put("name","최고운")
                    chat.put("text",message_edit.text.toString())

                    myRef.setValue(chat)
                }
            }
        }
    }

//    lateinit var myMessageItems: ArrayList<MyMessageItem>
//    lateinit var myMessageAdapter: MyMessageAdapter
//
//    lateinit var theirMessageItems: ArrayList<TheirMessageItem>
//    lateinit var theirMessageAdapter: TheirMessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halmate_message)

//
//        //말풍선을 아이템에 붙이는 부분임
//        myMessageItems = ArrayList()
//        myMessageItems.add(MyMessageItem( "안녕안녕", "오후 2:03"))
//        myMessageAdapter = MyMessageAdapter(myMessageItems, this)
//        my_message_list.layoutManager = LinearLayoutManager(this)
//        my_message_list.adapter = myMessageAdapter
//
//        theirMessageItems = ArrayList()
//        theirMessageItems.add(TheirMessageItem("안녕안녕", "오후 2:03"))
//        theirMessageAdapter = TheirMessageAdapter(theirMessageItems, this)
//        their_message_list.layoutManager = LinearLayoutManager(this)
//        their_message_list.adapter = theirMessageAdapter

    }
}