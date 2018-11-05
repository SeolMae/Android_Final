package com.team.halae

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

class HalmateDonateFragment() : Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener {

    lateinit var donateRecyclerView: RecyclerView
    lateinit var donateList: ArrayList<DonateListItem>
    lateinit var donateAdapter: DonateListAdapter
    lateinit var mGlideRequestManager: RequestManager

    var alignspinner: Spinner? = null
    var align: String? = null
    var halnameandage: String? = null
    private var networkService: NetworkService? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_hamate_donate, container, false)

        networkService = ApplicationController.instance!!.networkService
        donateRecyclerView = v.findViewById(R.id.donate_recyclerview)
        mGlideRequestManager = Glide.with(this)

        //------------------정렬 스피너------------------------
        alignspinner = v.findViewById(R.id.donate_align_spinner)

        var alignAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.정렬))
        alignspinner!!.adapter = alignAdapter
        alignspinner!!.setSelection(0)
        alignspinner!!.setOnItemSelectedListener(this)
        align = alignspinner!!.selectedItem.toString()

        //------------------------------기부글 recyclerview---------------------------------
        donateList = ArrayList<DonateListItem>()
        donateList.add(DonateListItem("https://s3.ap-northeast-2.amazonaws.com/halmate/%EB%B0%95%ED%83%9C%ED%99%98.jpg", "최고운 할머니는 맥북이 필요해요.","회고운할머니(67)","모금 목표 금액 3,000,000원","1,260,000원","42%","종료6일전"))
        donateList.add(DonateListItem("https://s3.ap-northeast-2.amazonaws.com/halmate/%EB%B0%95%ED%83%9C%ED%99%98.jpg", "최고운 할머니는 맥북이 필요해요.","회고운할머니(67)","모금 목표 금액 3,000,000원","1,260,000원","42%","종료6일전"))
        donateList.add(DonateListItem("https://s3.ap-northeast-2.amazonaws.com/halmate/%EB%B0%95%ED%83%9C%ED%99%98.jpg", "최고운 할머니는 맥북이 필요해요.","회고운할머니(67)","모금 목표 금액 3,000,000원","1,260,000원","42%","종료6일전"))
        donateRecyclerView!!.layoutManager = LinearLayoutManager(context)
        donateRecyclerView!!.setOnClickListener(this)

        return v

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

       /* var getdonatelist = networkService!!.getdonateList(align!!)

        getdonatelist.enqueue(object : retrofit2.Callback<DonateListResponse> {

            override fun onFailure(call: retrofit2.Call<DonateListResponse>?, t: Throwable?) {
                //ApplicationController.instance!!.makeToast("onFailure")
                Log.v("sylee", "donate list onfailure")
                Log.v("sylee", t.toString())
            }


            override fun onResponse(call: retrofit2.Call<DonateListResponse>?, response: Response<DonateListResponse>?) {
                if (response!!.isSuccessful) {
                    if (response.body().message.equals("get donate list Success")) {
                        Log.v("sylee", "status code 201")
                        donateAdapter = DonateListAdapter(donateList, mGlideRequestManager)
                        donateRecyclerView!!.adapter = donateAdapter

                        //결과 값 array 에 저장
                        var resultarry = response.body().result
                        var resultlen = resultarry.size

                        for (i in 1..resultlen) {

                            if (response.body().result[i].hal_gender == 0) {
                                halnameandage = response.body().result[i].hal_name + " 할아버지(" + response.body().result[i].hal_age.toString() + ")"
                            } else if (response.body().result[i].hal_gender == 1) {
                                halnameandage = response.body().result[i].hal_name + " 할아버지(" + response.body().result[i].hal_age.toString() + ")"
                            }

                            var goalmoney = "모금 목표 금액 : " + response.body().result[i].goal_money.toString() + "원"
                            var nowmoney = response.body().result[i].now_money.toString() + " 원"
                            var percentage = ((response.body().result[i].now_money / response.body().result[i].goal_money) * 100).toString() + "%"
                            var leftdays = "종료 6일 전"

                            donateList.add(DonateListItem(response.body().result[i].hal_img, response.body().result[i].don_title, halnameandage!!, goalmoney,nowmoney,percentage,leftdays))
                        }
                    }
                }
            }
        })*/
    }

    override fun onClick(v: View?) {
        //클릭한 항목의 인덱스를 저장
        val idx: Int = donateRecyclerView!!.getChildAdapterPosition(v!!)

        val img: String? = donateList[idx].donateimg
        val title: String? = donateList[idx].donatetitle
        val name: String? = donateList[idx].donatehalmatename
        val goalmoney: String? = donateList[idx].donategoalmoney
        val nowmoney: String? = donateList[idx].donatenowmoney
        val percent: String? = donateList[idx].donatepercent
        val leftdays: String? = donateList[idx].donateleftday

        val intent = Intent(activity, DonateDatailActivity::class.java)

        intent.putExtra("img", img)
        intent.putExtra("name", name)
        intent.putExtra("age", title)
        intent.putExtra("goalmoney", goalmoney)
        intent.putExtra("nowmoney", nowmoney)
        intent.putExtra("percent", percent)
        intent.putExtra("leftdays", leftdays)

        startActivity(intent)

    }
}
