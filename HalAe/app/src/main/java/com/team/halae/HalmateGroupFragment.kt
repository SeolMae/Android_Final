package com.team.halae

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_halmate.*
import kotlinx.android.synthetic.main.fragment_halmate_group.*

class HalmateGroupFragment : Fragment() {

    lateinit var groupItems: ArrayList<GroupItem>
    lateinit var groupAdapter: GroupAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_halmate_group,container,false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        groupItems = ArrayList()
        groupItems.add(GroupItem(R.drawable.sample, "최고운"))
        groupItems.add(GroupItem(R.drawable.sample, "이선영"))
        groupItems.add(GroupItem(R.drawable.sample, "최윤호"))
        groupItems.add(GroupItem(R.drawable.sample, "양시연"))

//        var addImage : ImageView = v.findViewById(R.id.group_add5)
//        addImage.visibility


        groupAdapter = GroupAdapter(groupItems)
        halmate_group_list.layoutManager = GridLayoutManager(this.context, 4)
        halmate_group_list.adapter = groupAdapter
    }
}