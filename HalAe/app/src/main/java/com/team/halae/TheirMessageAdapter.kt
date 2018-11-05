package com.team.halae

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TheirMessageAdapter( var theirMessageItems : ArrayList<TheirMessageItem>, var context: Context) : RecyclerView.Adapter<TheirMessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheirMessageViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.their_message_item, parent, false)

        return TheirMessageViewHolder(mainView)
    }

    override fun getItemCount(): Int = theirMessageItems.size

    override fun onBindViewHolder(holder: TheirMessageViewHolder, position: Int) {
        holder.theirProfile.setImageResource(theirMessageItems[position].theirProfile)
        holder.theirName.text = theirMessageItems[position].theirName
        holder.theirMessageText.text = theirMessageItems[position].theirMessage
        holder.theirMessageTime.text = theirMessageItems[position].theirTime


    }
}