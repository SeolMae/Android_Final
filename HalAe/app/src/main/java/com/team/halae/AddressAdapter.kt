package com.team.halae

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.team.halae.R.id.address_list
import kotlinx.android.synthetic.main.activity_login_search1.*

class AddressAdapter(var addressItems : ArrayList<String>, private var context : Context) : RecyclerView.Adapter<AddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.address_item, parent, false)
        return AddressViewHolder(mainView)
    }

    override fun getItemCount(): Int = addressItems.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.address.text = addressItems[position]

        holder.itemView.setOnClickListener{
            val intent = Intent(context, LoginSearchActivity2::class.java)
            intent.putExtra("address_result_keyword", addressItems[position])
            context.startActivity(intent)
        }

    }
}