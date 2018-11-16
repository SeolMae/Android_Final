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
    private var filteringrequestbody : FilteringRequestBody = FilteringRequestBody("위치", 2, "관심분야")

    lateinit var setResultBody : ArrayList<SearchItem>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        networkService = ApplicationController.instance!!.networkService

        mGlideRequestManager = Glide.with(this)
        setResultBody = ArrayList()

        val v = inflater.inflate(R.layout.fragment_halmate_search,container,false)
        searchRecyclerView = v.findViewById(R.id.halmate_search_list)

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


        //----------------------찾기 버튼--------------------------
        searchbtn = v.findViewById(R.id.search_search_btn)
        searchbtn!!.setOnClickListener(this)


        searchRecyclerView!!.layoutManager = GridLayoutManager(this.context, 2)
        searchRecyclerView.setOnClickListener(this)


       return v

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        when(p0){
            locationSpinner -> {
                Log.v("sylee", "location spinner test")
                location = locationSpinner!!.selectedItem.toString()
                if(location == "위치") filteringrequestbody!!.hal_address = "위치"
                filteringrequestbody!!.hal_address = location
            }
            genderSpinner -> {
                Log.v("sylee", "gender spinner test")
                gender = genderSpinner!!.selectedItem.toString()
                Log.v("sylee", gender)

                if(gender == "남"){
                    filteringrequestbody!!.hal_gender = 0
                }
                else if(gender == "여") {
                    filteringrequestbody!!.hal_gender = 1
                }else if(gender == "성별"){
                    filteringrequestbody!!.hal_gender = 2
                }

            }
            interestSpinner ->{
                Log.v("sylee", "interest spinner test")
                interest = interestSpinner!!.selectedItem.toString()
                Log.v("sylee", interest)
                if(interest == "관심분야") filteringrequestbody!!.hal_interest = "관심분야"
                filteringrequestbody!!.hal_interest = interest
            }
        }

        var posthalmatefilering = networkService!!.postHalmateFitering(filteringrequestbody!!)

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

        })
    }

    override fun onClick(v: View?) {    //onclicklistner
        Log.v("sylee", "여기는 ?? 들어올듯?")

        when(v){
            searchbtn ->{
                val intent = Intent(activity, SearchMagnifyingActivity::class.java)
                startActivity(intent)

            }else->{
            //클릭한 항목의 인덱스를 저장
            val idx : Int = searchRecyclerView!!.getChildAdapterPosition(v!!)

            val img : String? = setResultBody[idx].image
            val name : String? = setResultBody[idx].name
            val age : Int? = setResultBody[idx].age
            val address : String? = setResultBody[idx].address
            val interest : String? = setResultBody[idx].interest

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