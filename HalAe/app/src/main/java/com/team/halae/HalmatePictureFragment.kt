package com.team.halae

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HalmatePictureFragment : Fragment() {

    lateinit var pictureItems: ArrayList<Int>
    lateinit var pictureAdapter: PictureAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_halmate_picture,container,false)

        pictureItems = ArrayList()
        pictureItems.add(R.drawable.sample)
        pictureItems.add(R.drawable.sample)
        pictureItems.add(R.drawable.sample)

        pictureAdapter = PictureAdapter(pictureItems)
        var recyclerView : RecyclerView = v.findViewById(R.id.halmate_picture_list)
        recyclerView.layoutManager = GridLayoutManager(this.context, 3)
        recyclerView.adapter = pictureAdapter

        return v
    }
}