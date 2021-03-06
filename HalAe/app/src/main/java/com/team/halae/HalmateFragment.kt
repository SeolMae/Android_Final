package com.team.halae

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.team.halae.R.id.*
import kotlinx.android.synthetic.main.fragment_halmate.*
import kotlinx.android.synthetic.main.fragment_halmate.view.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class HalmateFragment : Fragment(), View.OnClickListener {


    lateinit var networkService: NetworkService
    private var requestManager: RequestManager? = null
    var index = 0


    override fun onClick(v: View?) {
        when(v!!){
            halmate_schedule_tab->{
                clearSelected()
                halmate_schedule_tab.isSelected = true

                var fragment : Fragment = HalmateScheduleFragment()
                var bundle : Bundle = Bundle()
                bundle.putInt("hal_idx", index!!)
                fragment!!.arguments = bundle

                replaceFragment(fragment)

            }

            halmate_picture_tab->{
                clearSelected()
                halmate_picture_tab.isSelected = true

                var fragment : Fragment = HalmatePictureFragment()
                var bundle : Bundle = Bundle()
                bundle.putInt("hal_idx", index!!)
                fragment!!.arguments = bundle

                replaceFragment(fragment)
            }

            halmate_board_tab->{
                clearSelected()
                halmate_board_tab.isSelected = true

                var fragment : Fragment = HalmateBoardFragment()
                var bundle : Bundle = Bundle()
                bundle.putInt("hal_idx", index!!)
                fragment!!.arguments = bundle

                replaceFragment(fragment)
            }

            halmate_group_tab->{
                clearSelected()
                halmate_group_tab.isSelected = true

                var fragment : Fragment = HalmateGroupFragment()
                var bundle : Bundle = Bundle()
                bundle.putInt("hal_idx", index!!)
                fragment!!.arguments = bundle

                replaceFragment(fragment)

                halmate_message_icon.visibility = View.VISIBLE
            }

            halmate_message_icon->{
                startActivity(Intent(context, HalmateMessageActivity::class.java))
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_halmate, container, false)

        val args = arguments
        index = args!!.getInt("hal_idx")
        v.halmate_frame
        v.halmate_message_icon.setOnClickListener(this)
        v.halmate_schedule_tab.setOnClickListener(this)
        v.halmate_picture_tab.setOnClickListener(this)
        v.halmate_board_tab.setOnClickListener(this)
        v.halmate_group_tab.setOnClickListener(this)

        var fragment : Fragment = HalmateScheduleFragment()
        var bundle : Bundle = Bundle()
        bundle.putInt("hal_idx", index!!)
        fragment!!.arguments = bundle
        addFragment(fragment)

        //통신
        Log.e("할메이트",index.toString())
        networkService = ApplicationController.instance.networkService
        requestManager = Glide.with(this)
        var halmateInformationResponse  = networkService.getHalmateInformation(index.toString())
        halmateInformationResponse.enqueue(object : retrofit2.Callback<HalmateInformationResponse> {
            override fun onResponse(call: Call<HalmateInformationResponse>?, response: Response<HalmateInformationResponse>?) {
                if(response!!.isSuccessful){
//                    halmate_info_image.setImage(response.body().result.hal_img.)
                    Glide.with(context).load(response.body().result.hal_img).into(halmate_info_image)
                    halmate_info_name.text = response.body().result.hal_name
                    halmate_add.text = response.body().result.hal_address
                    halmate_phone.text = response.body().result.hal_phone
                    halmate_info_vol.text = "봉사자 " + response.body().result.vol_cnt.toString() + "명"
                    Log.e("통신성공",response.body().message)
                }
            }

            override fun onFailure(call: Call<HalmateInformationResponse>?, t: Throwable?) {
                Log.e("통신오류",t.toString())
            }

        })

//        var scheduleTab : ImageView = v.findViewById(R.id.halmate_schedule_tab)
//        halmate_schedule_tab.isSelected = true

        return v
    }

    fun addFragment(fragment: Fragment){
        if(!fragment.isAdded){
            val fm = childFragmentManager
            val transaction = fm.beginTransaction()
            transaction.add(R.id.halmate_frame, fragment)
            transaction.commit()
        }
    }

    fun replaceFragment(fragment: Fragment){
        if(!fragment.isAdded){
            val fm = childFragmentManager
            val transaction = fm.beginTransaction()
            transaction.replace(R.id.halmate_frame, fragment)
            transaction.commit()
        }
    }

    fun clearSelected(){
        halmate_schedule_tab.isSelected = false
        halmate_picture_tab.isSelected = false
        halmate_board_tab.isSelected = false
        halmate_group_tab.isSelected = false
        halmate_message_icon.visibility = View.INVISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        halmate_schedule_tab.isSelected = true
    }

}