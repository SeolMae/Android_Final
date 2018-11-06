package com.team.halae

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager

class CommentAdapter (var dataList: ArrayList<CommentData>?, var requestManager: RequestManager) : RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): CommentViewHolder {
        val mainView : View = LayoutInflater.from(p0!!.context).inflate(R.layout.comment_items, p0, false)
        mainView.setOnClickListener(onItemClick)
        return CommentViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder!!.commentName.text = dataList!!.get(position).usr_id
        holder!!.commentContent.text = dataList!!.get(position).cmt_text
    }

    private var onItemClick : View.OnClickListener? = null

    fun setOnItemClickListener (l : View.OnClickListener) {
        onItemClick = l
    }

}

