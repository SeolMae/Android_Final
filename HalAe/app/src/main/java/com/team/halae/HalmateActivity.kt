package com.team.halae

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_halmate.*
import kotlinx.android.synthetic.main.fragment_halmate.view.*
import android.content.Context.LAYOUT_INFLATER_SERVICE



class HalmateActivity : Fragment(), View.OnClickListener {


//    var scheduleTab : ImageView = v.findViewById(R.id.halmate_schedule_tab)
//    var pictureTab : ImageView = v.findViewById(R.id.halmate_picture_tab)
//    var boardTab : ImageView = v.findViewById(R.id.halmate_board_tab)
//    var groupTab : ImageView = v.findViewById(R.id.halmate_group_tab)
    override fun onClick(v: View?) {
        when(v!!){
            halmate_schedule_tab->{
//                clearSelected()
//                scheduleTab.isSelected = true
                replaceFragment(HalmateScheduleFragment())
            }

            halmate_picture_tab->{
//                clearSelected()
//                pictureTab.isSelected = true
                replaceFragment(HalmatePictureFragment())
            }

            halmate_board_tab->{
//                clearSelected()
//                boardTab.isSelected = true
                replaceFragment(HalmateBoardFragment())
            }

            halmate_group_tab->{
//                clearSelected()
//                groupTab.isSelected = true
                replaceFragment(HalmateGroupFragment())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_halmate, container, false)

        v.halmate_frame
        v.halmate_schedule_tab.setOnClickListener(this)
        v.halmate_picture_tab.setOnClickListener(this)
        v.halmate_board_tab.setOnClickListener(this)
        v.halmate_group_tab.setOnClickListener(this)

        addFragment(HalmateScheduleFragment())

        var scheduleTab : ImageView = v.findViewById(R.id.halmate_schedule_tab)
        scheduleTab.isSelected = true

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

//    fun clearSelected(){
//        scheduleTab.isSelected = false
//        pictureTab.isSelected = false
//        boardTab.isSelected = false
//        groupTab.isSelected = false
//    }

}
