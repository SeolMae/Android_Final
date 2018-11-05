package com.team.halae

import android.support.v4.view.NestedScrollingParent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import com.bumptech.glide.RequestManager

class PostAdapter (var dataList : ArrayList<BoardData>?, var requestManager: RequestManager) : RecyclerView.Adapter<PostViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): PostViewHolder {
        val mainView : View = LayoutInflater.from(p0!!.context).inflate(R.layout.post_items, p0, false)
        mainView.setOnClickListener(onItemClick)
        return PostViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        requestManager.load(dataList!!.get(position).usr_img).into(holder!!.userImg)
        holder!!.userName.text = dataList!!.get(position).usr_name
        holder!!.postDate.text = dataList!!.get(position).board_date
        holder!!.postName.text = dataList!!.get(position).board_title
        holder!!.postContent.text = dataList!!.get(position).board_desc
        requestManager.load(dataList!!.get(position).board_img).into(holder!!.postImg)
    }

    fun setOnItemClickListener (l : View.OnClickListener){
        onItemClick = l
    }


}