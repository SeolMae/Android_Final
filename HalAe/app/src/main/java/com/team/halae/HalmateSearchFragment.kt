package com.team.halae

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Response


class HalmateSearchFragment() : Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener{

    lateinit var searchRecyclerView : RecyclerView
    lateinit var searchResult: ArrayList<SearchItem>
    lateinit var searchAdapter: SearchAdapter

    var locationSpinner : Spinner? = null
    var genderSpinner : Spinner? = null
    var interestSpinner : Spinner? = null

    var searchbtn : Button? = null

    lateinit var mGlideRequestManager : RequestManager

    lateinit var location : String
    lateinit var gender : String
    var intgender = 2
    lateinit var interest : String

    private var networkService : NetworkService? = null
    private var filteringrequestbody : FilteringRequestBody? = null
    private var filteringdefaultbody  : FilteringRequestBody? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        networkService = ApplicationController.instance!!.networkService

        mGlideRequestManager = Glide.with(this)

        val v = inflater.inflate(R.layout.fragment_halmate_search,container,false)
        searchRecyclerView = v.findViewById(R.id.halmate_search_list)
        searchResult = ArrayList()
        searchAdapter = SearchAdapter(searchResult, mGlideRequestManager)
        searchAdapter!!.setOnItemClickListner(this)


        //-----------------------------디폴트 리스트 받아오기----------------------------------
/*
        searchResult.add(SearchItem(R.drawable.sample, "박태환 할아버지", 78,"서울시 중구 필동", "#사진#독서"))
        searchResult.add(SearchItem(R.drawable.sample, "최서정 할머니", 78,"서울시 성북구 길음동", "#사진#독서"))
        searchResult.add(SearchItem(R.drawable.sample, "최서정 할머니", 78,"서울시 성북구 길음동", "#사진#독서"))
        searchResult.add(SearchItem(R.drawable.sample, "최서정 할머니", 78,"서울시 성북구 길음동", "#사진#독서"))
        searchResult.add(SearchItem(R.drawable.sample, "최서정 할머니", 78,"서울시 성북구 길음동", "#사진#독서"))
        searchResult.add(SearchItem(R.drawable.sample, "최서정 할머니", 78,"서울시 성북구 길음동", "#사진#독서"))
*/

        //-----------------스피너----------------
        locationSpinner = v.findViewById(R.id.search_spinner_location)
        genderSpinner = v.findViewById(R.id.search_spinner_gender)
        interestSpinner = v.findViewById(R.id.search_spinner_interest)


        var locationAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.위치))
        locationSpinner!!.adapter = locationAdapter
        locationSpinner!!.setSelection(0)

        var genderAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.성별))
        genderSpinner!!.adapter = genderAdapter
        genderSpinner!!.setSelection(0)

        var interestAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.관심분야))
        interestSpinner!!.adapter = interestAdapter
        interestSpinner!!.setSelection(0)

        locationSpinner!!.setOnItemSelectedListener(this)
        genderSpinner!!.setOnItemSelectedListener(this)
        interestSpinner!!.setOnItemSelectedListener(this)

        searchRecyclerView!!.layoutManager = GridLayoutManager(this.context, 2)
        searchRecyclerView!!.adapter = searchAdapter
        searchRecyclerView.setOnClickListener(this)

        //----------------------찾기 버튼--------------------------
        searchbtn = v.findViewById(R.id.search_search_btn)
        searchbtn!!.setOnClickListener(this)


       return v

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        when(p0){
            locationSpinner -> {
                Log.v("sylee", "location spinner test")
                Toast.makeText(context, "location",Toast.LENGTH_LONG).show()
                location = locationSpinner!!.selectedItem.toString()
                if(location == "위치") location = ""
                filteringrequestbody!!.hal_address = null.toString()
            }
            genderSpinner -> {
                Log.v("sylee", "gender spinner test")
                Toast.makeText(context, "gender" + p2, Toast.LENGTH_LONG).show()
                gender = genderSpinner!!.selectedItem.toString()
                Log.v("sylee", gender)

                if(gender == "남"){
                    filteringrequestbody!!.hal_gender = 0
                }
                else if(gender == "여") {
                    filteringrequestbody!!.hal_gender = 1
                }else if(gender == "성별"){
                    filteringrequestbody!!.hal_gender = null
                }

            }
            interestSpinner ->{
                Log.v("sylee", "interest spinner test")
                Toast.makeText(context, "interest" + p2, Toast.LENGTH_LONG).show()
                interest = interestSpinner!!.selectedItem.toString()
                Log.v("sylee", interest)
                if(interest == "관심분야") interest = ""
                filteringrequestbody!!.hal_interest = null.toString()
            }
        }



        var posthalmatefilering = networkService!!.postHalmateFitering(filteringrequestbody!!)
        posthalmatefilering.enqueue(object : retrofit2.Callback<FilteringPostResponse> {
            override fun onResponse(call: Call<FilteringPostResponse>?, response: Response<FilteringPostResponse>?) {
                Log.v("sylee",response!!.message().toString())
                if(response!!.isSuccessful){
                    Log.v("sylee","halmate search isSuccessful")

                    if(response.body().message.equals("get halmate schedule information Success")){
                        Log.v("sylee","status code 201")
                        Log.v("sylee", response.body().result[0].toString())
                        //for(i in)


                    }
                }
            }

            override fun onFailure(call: Call<FilteringPostResponse>?, t: Throwable?) {
                //ApplicationController.instance!!.makeToast("onFailure")
                Log.v("sylee","default list onfailure")
                Log.v("sylee", t.toString())
                Log.v("sylee",filteringrequestbody.toString())

            }

        })
    }

    override fun onClick(v: View?) {    //onclicklistner

        when(v){
            searchbtn ->{
                val intent = Intent(activity, SearchMagnifyingActivity::class.java)
                startActivity(intent)

            }else->{
            //클릭한 항목의 인덱스를 저장
            val idx : Int = searchRecyclerView!!.getChildAdapterPosition(v!!)

            val img : String? = searchResult[idx].image
            val name : String? = searchResult[idx].name
            val age : Int? = searchResult[idx].age
            val address : String? = searchResult[idx].address
            val interest : String? = searchResult[idx].interest

            val intent = Intent(activity, SearchDetailActivity::class.java)

            intent.putExtra("img", img)
            intent.putExtra("name", name)
            intent.putExtra("age", age)
            intent.putExtra("address", address)
            intent.putExtra("interest", interest)

            startActivity(intent)
            }

        }


    }

}