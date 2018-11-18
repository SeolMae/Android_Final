package com.team.halae

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.fragment_halmate.*
import kotlinx.android.synthetic.main.fragment_halmate_group.*
import retrofit2.Call
import retrofit2.Response

class HalmateGroupFragment : Fragment() {

    lateinit var groupItems: ArrayList<HalmateGroupMemarry>
    lateinit var groupAdapter: GroupAdapter
    lateinit var networkService: NetworkService
    lateinit var requestManager: RequestManager
    lateinit var addImage : RelativeLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_halmate_group,container,false)

        val args = arguments
        val index = args!!.getInt("hal_idx")
        var recyclerView : RecyclerView = v.findViewById(R.id.halmate_group_list)
        networkService = ApplicationController.instance.networkService
        requestManager = Glide.with(this)
        recyclerView.layoutManager = GridLayoutManager(this.context, 4)
        var halmateGroupResponse  = networkService.getHalmateGroup(index.toString())
        halmateGroupResponse.enqueue(object : retrofit2.Callback<HalmateGroupResponse> {
            override fun onResponse(call: Call<HalmateGroupResponse>?, response: Response<HalmateGroupResponse>?) {
                if(response!!.isSuccessful){
                    group_intro.text = response.body().result.groupintro
                    groupItems = response.body().result.groupmemarry
                    groupAdapter = GroupAdapter(groupItems, requestManager)
                    recyclerView.adapter = groupAdapter
                    var memSize = response.body().result.groupmemarry.size
                    when (memSize){
                        0->{
                            addImage = v.findViewById(R.id.group_add1)
                            addImage.visibility = v.visibility
                        }
                        1->{
                            addImage = v.findViewById(R.id.group_add2)
                            addImage.visibility = v.visibility
                        }
                        2->{
                            addImage = v.findViewById(R.id.group_add3)
                            addImage.visibility = v.visibility
                        }
                        3->{
                            addImage = v.findViewById(R.id.group_add4)
                            addImage.visibility = v.visibility
                        }
                        4->{
                            addImage = v.findViewById(R.id.group_add5)
                            addImage.visibility = v.visibility
                        }
                        5->{
                            addImage = v.findViewById(R.id.group_add6)
                            addImage.visibility = v.visibility
                        }
                        6->{
                            addImage = v.findViewById(R.id.group_add7)
                            addImage.visibility = v.visibility
                        }

                    }

                    Log.e("안녕",response.body().message)
                }
            }

            override fun onFailure(call: Call<HalmateGroupResponse>?, t: Throwable?) {
                Log.e("통신오류",t.toString())
            }

        })
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        groupItems = ArrayList()
//        groupItems.add(GroupItem(R.drawable.sample, "최고운"))
//        groupItems.add(GroupItem(R.drawable.sample, "이선영"))
//        groupItems.add(GroupItem(R.drawable.sample, "최윤호"))
//        groupItems.add(GroupItem(R.drawable.sample, "양시연"))

//        var addImage : ImageView = v.findViewById(R.id.group_add5)
//        addImage.visibility


//        groupAdapter = GroupAdapter(groupItems)
//        halmate_group_list.layoutManager = GridLayoutManager(this.context, 4)
//        halmate_group_list.adapter = groupAdapter
    }
}