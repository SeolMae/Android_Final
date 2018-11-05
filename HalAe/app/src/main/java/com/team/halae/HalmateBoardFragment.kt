package com.team.halae

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_halmate_board.*

class HalmateBoardFragment : Fragment() {

    lateinit var boardItems: ArrayList<BoardItem>
    lateinit var boardAdapter: BoardAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_halmate_board,container,false)

        boardItems = ArrayList()
        boardItems.add(BoardItem(R.drawable.sample, "제목입니다1","날짜입니다1"))
        boardItems.add(BoardItem(R.drawable.sample, "제목입니다2","날짜입니다2"))
        boardItems.add(BoardItem(R.drawable.sample, "제목입니다3","날짜입니다3"))

        boardAdapter = BoardAdapter(boardItems)
        var recyclerView : RecyclerView = v.findViewById(R.id.halmate_board_list)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = boardAdapter
        return v
    }
}