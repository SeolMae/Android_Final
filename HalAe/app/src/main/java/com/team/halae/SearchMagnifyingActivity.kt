package com.team.halae

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class SearchMagnifyingActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var searchword : EditText
    lateinit var canclebtn : Button
    lateinit var deleterecordbtn : Button

    lateinit var recordRecyclerView: RecyclerView
    lateinit var searchrecordList: ArrayList<SearchMagnifyingItem>
    lateinit var recordAdapter: SearchMagnifyingAdpater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_magnifying)

        searchword = findViewById(R.id.searching_edittext)
        canclebtn = findViewById(R.id.searching_cancle_button)
        deleterecordbtn = findViewById(R.id.record_delete_button)


        recordRecyclerView = findViewById(R.id.halmate_search_record)

        searchrecordList = ArrayList<SearchMagnifyingItem>()
        searchrecordList.add(SearchMagnifyingItem(R.drawable.search_search_searchbottom,"최윤호"))
        searchrecordList.add(SearchMagnifyingItem(R.drawable.search_search_searchbottom,"그림"))
        searchrecordList.add(SearchMagnifyingItem(R.drawable.search_search_searchbottom,"양시연 할머니"))
        searchrecordList.add(SearchMagnifyingItem(R.drawable.search_search_searchbottom,"신당동"))
        searchrecordList.add(SearchMagnifyingItem(R.drawable.search_search_searchbottom,"최고운 할머니"))
        searchrecordList.add(SearchMagnifyingItem(R.drawable.search_search_searchbottom,"춤추기"))

        recordAdapter = SearchMagnifyingAdpater(searchrecordList!!)
        recordAdapter.setOnItemClickListner(this)

        recordRecyclerView.layoutManager = LinearLayoutManager(this)
        recordRecyclerView!!.adapter = recordAdapter


        canclebtn!!.setOnClickListener(this)
        deleterecordbtn!!.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0){
            canclebtn->{
                Log.v("sylee", "취소 버튼 test")
                onBackPressed()
            }
            deleterecordbtn->{
                //서버에 검색 기록 삭제

            }

        }
    }

}
