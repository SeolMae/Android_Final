package com.team.halae

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class SearchDetailActivity : AppCompatActivity(), View.OnClickListener {

    private var volunteerRecyclerview : RecyclerView? = null
    private var volunteerList : ArrayList<SearchVolunteerItem>? = null
    private var volunteerAdapter : SearchVolunteerAdapter? = null

    private var activityRecyclerview : RecyclerView? = null
    private var activityList : ArrayList<SearchActivityItem>? = null
    private var activityAdapter : SearchActivityAdapter? = null

    private var horizonmanager1 : LinearLayoutManager? = null
    private var horizonmanager2 : LinearLayoutManager? = null

    private var networkservice : NetworkService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_detail)

        //--------------------------봉사자들----------------------------------

        horizonmanager1 = LinearLayoutManager(this)
        horizonmanager1!!.orientation = LinearLayoutManager.HORIZONTAL

        volunteerRecyclerview = findViewById(R.id.volunteer_list)
        volunteerRecyclerview!!.layoutManager = horizonmanager1

        volunteerList = ArrayList<SearchVolunteerItem>()

        //var posthalmatefilering = networkservice!!.getHalmateInformation()
        /*
        Log.v("sylee", filteringrequestbody.toString())

        posthalmatefilering.enqueue(object : retrofit2.Callback<FilteringPostResponse> {
            override fun onResponse(call: Call<FilteringPostResponse>?, response: Response<FilteringPostResponse>?) {

                Log.v("sylee",response!!.message().toString())
                if(response!!.isSuccessful){
                    Log.v("sylee","halmate search isSuccessful")

                    if(response.body().message.equals("get halmate schedule information Success")){
                        Log.v("sylee", response.body().result.toString())

                        setResultBody.clear()

                        var sethalname : String
                        var sethalinterest : String = ""

                        for(i in 0..(response.body().result.size)-1){

                            if(response.body().result[i].hal_gender == 0){
                                sethalname = response.body().result[i].hal_name + " 할아버지"
                            }else{
                                sethalname = response.body().result[i].hal_name + " 할머니"
                            }

                            sethalinterest = ""

                            for(j in 0..(response.body().result[i].interestArry.size-1)){
                                sethalinterest += "#" + response.body().result[i].interestArry[j]
                            }


                            var tempSearchItem : SearchItem
                                    = SearchItem(response.body().result[i].hal_img, sethalname,
                                    response.body().result[i].hal_age, response.body().result[i].hal_address, sethalinterest)

                            Log.v("sylee", sethalinterest)
                            setResultBody.add(tempSearchItem)

                        }

                        searchAdapter = SearchAdapter(setResultBody!!, mGlideRequestManager)
                        searchRecyclerView!!.adapter = searchAdapter
                        searchAdapter!!.setOnItemClickListner(this@HalmateSearchFragment)

                    }
                }
            }


            override fun onFailure(call: Call<FilteringPostResponse>?, t: Throwable?) {

                Log.v("sylee",filteringrequestbody.toString())
                Log.v("sylee", t.toString())

            }

        })*/

        volunteerList!!.add(SearchVolunteerItem(R.drawable.sample, "최고운"))
        volunteerList!!.add(SearchVolunteerItem(R.drawable.sample, "이선영"))
        volunteerList!!.add(SearchVolunteerItem(R.drawable.sample, "신예지"))
        volunteerList!!.add(SearchVolunteerItem(R.drawable.sample, "이승민"))
        volunteerList!!.add(SearchVolunteerItem(R.drawable.sample, "박선희"))

        volunteerAdapter = SearchVolunteerAdapter(volunteerList!!)

        volunteerAdapter!!.setOnItemClickListner(this)
        volunteerRecyclerview!!.adapter = volunteerAdapter

        //----------------------------활동 글--------------------------------------

        horizonmanager2 = LinearLayoutManager(this)
        horizonmanager2!!.orientation = LinearLayoutManager.HORIZONTAL


        activityRecyclerview = findViewById(R.id.activity_list)
        activityRecyclerview!!.layoutManager = horizonmanager2

        activityList = ArrayList<SearchActivityItem>()
        activityList!!.add(SearchActivityItem(R.drawable.sample,"할머니 집 청소!"))
        activityList!!.add(SearchActivityItem(R.drawable.sample,"연탄 함께 날라요!"))
        activityList!!.add(SearchActivityItem(R.drawable.sample,"밥한술 문짝 달아봐요!"))

        activityAdapter = SearchActivityAdapter(activityList!!)
        activityAdapter!!.setOnItemClickListner(this)
        activityRecyclerview!!.adapter = activityAdapter



    }

    override fun onClick(v: View?) {    //onclicklistner

    }
}