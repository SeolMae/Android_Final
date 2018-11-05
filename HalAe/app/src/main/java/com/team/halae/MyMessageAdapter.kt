package com.team.halae

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MyMessageAdapter( var myMessageItems : ArrayList<MyMessageItem>, var context: Context) : RecyclerView.Adapter<MyMessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMessageViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.my_message_bubble_item, parent, false)

        return MyMessageViewHolder(mainView)
    }

    override fun getItemCount(): Int = myMessageItems.size

    override fun onBindViewHolder(holder: MyMessageViewHolder, position: Int) {
        holder.myMessageText.text = myMessageItems[position].myMessage
        holder.myMessageTime.text = myMessageItems[position].myTime


    }
}