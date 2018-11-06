package com.team.halae

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MywriteFragment : Fragment() {

    val TAG : String = " LOG::WriteFragment"

    var token : String = " "
    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    private var myboardLists : RecyclerView? = null
    private var adapter : MywriteAdapter? = null
    private var myboardDatas : ArrayList<MyBoardData>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_mywrite, container, false)

        Log.v(TAG, "onCreateView")
        if(arguments != null){
            //v!!.first_text.text = arguments.getString("title")
        }
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "onCreate")


        val getUsrBoardListResponse = networkService!!.getUsrBoardList(token)
        getUsrBoardListResponse.enqueue(object : retrofit2.Callback<UsrBoardListResponse> {
            override fun onFailure(call: Call<UsrBoardListResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<UsrBoardListResponse>?, response: Response<UsrBoardListResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().message =="Successfully get board list"){
                        adapter = MywriteAdapter(response.body().data, requestManager!!)
                        myboardDatas = response.body().data
                        myboardLists!!.adapter = adapter
                    }
                }
            }

        })

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
