package com.team.halae

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MymarkFragment : Fragment() {

    val TAG : String = " LOG::WriteFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_mymark, container, false)

        Log.v(TAG, "onCreateView")
        if(arguments != null){
            //v!!.first_text.text = arguments.getString("title")
        }
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "onResume")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.v(TAG, "onAttach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy")
    }

    override fun onPause() {
        super.onPause()
//        EventBus.getInstance().unregister(this)
        Log.v(TAG, "onPause")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v(TAG, "onViewCreated")
    }

    override fun onDetach() {
        super.onDetach()
        Log.v(TAG, "onDetach")
    }
}
