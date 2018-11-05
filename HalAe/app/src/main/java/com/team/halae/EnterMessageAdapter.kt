package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class EnterMessageAdapter(var enterMessageItems : ArrayList<EnterMessageItem>) : RecyclerView.Adapter<EnterMessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnterMessageViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.message_enter_item, parent, false)
        return EnterMessageViewHolder(mainView)
    }

    override fun getItemCount(): Int = enterMessageItems.size

    override fun onBindViewHolder(holder: EnterMessageViewHolder, position: Int) {
        holder.enterMessage.text = enterMessageItems[position].enterMessage
    }
}