package com.team.halae

import android.app.usage.UsageEvents
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.INotificationSideChannel
import android.util.EventLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarDay.from
import com.prolificinteractive.materialcalendarview.CalendarMode
import android.util.Log
import android.widget.TextView
import jp.co.recruit_mp.android.lightcalendarview.LightCalendarView
import jp.co.recruit_mp.android.lightcalendarview.MonthView
import jp.co.recruit_mp.android.lightcalendarview.WeekDay
import jp.co.recruit_mp.android.lightcalendarview.accent.Accent
import jp.co.recruit_mp.android.lightcalendarview.accent.DotAccent
import kotlinx.android.synthetic.main.fragment_halmate.*
import kotlinx.android.synthetic.main.fragment_halmate_schedule.*
import kotlinx.android.synthetic.main.fragment_halmate_schedule.view.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HalmateScheduleFragment : Fragment() {

    lateinit var networkService: NetworkService
    lateinit var calendarView: LightCalendarView
    var currentDay : ArrayList<Int> = ArrayList()

    private val formatter = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
//    lateinit var scheduledDate : ArrayList<Int>

    var monthIndex = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_halmate_schedule,container,false)

//        val materialCalendarView : CalendarView = v.findViewById(R.id.calendarView)
//        materialCalendarView?.setOnDateChangeListener{view, year, month, dayOfMonth ->
//            startActivity(Intent(context, HalmateScheduleSelectActivity::class.java))
//        }



        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView = view.findViewById(R.id.calendarView) as? LightCalendarView ?: throw IllegalStateException("calendarView not found")

        calendarView.setWeekDayFilterColor(WeekDay.SUNDAY, Color.RED)
        calendarView.setDayFilterColor(WeekDay.SUNDAY, Color.RED)
        calendarView.setWeekDayFilterColor(WeekDay.SATURDAY, Color.BLUE)
        calendarView.setDayFilterColor(WeekDay.SATURDAY, Color.BLUE)

        ///////////////////////////////////////////////////////

        calendarView.setOnStateUpdatedListener(object : LightCalendarView.OnStateUpdatedListener{

            override fun onDateSelected(date: Date) {

                val intent = Intent(context,HalmateScheduleSelectActivity::class.java)

//                var currentMonth = formatter.format(date)[0].toString() + formatter.format(date)[1].toString()

                Log.e("모야",monthIndex.toString())
                var s = date.toString()
                s = s[8].toString() + s[9].toString()
                Log.e("엥",s)
                for(i in 0 until currentDay.size){
                    Log.e("포문","ㅇㅇ")
                    if(currentDay[i] == s.toInt()){
                        Log.e("이프문",i.toString())
                        intent.putExtra("selectDay",i.toString())
                    }
                    else{
                        intent.putExtra("selectDay","empty")
                    }
                }
                intent.putExtra("selectMonth",monthIndex.toString())
                startActivity(intent)
            }


            //map에 date만 통신후에 수정하기!
            //isScheduled 함수 짜서 isScheduled(it) 가 true 인 날짜에만 dotted!
//            isScheduled(it,scheduledDate ) == true
            override fun onMonthSelected(date: Date, view: MonthView) {
//                val title = view.findViewById<TextView>(R.id.calendar_month)
//                title.text = formatter.format(date)


                currentDay.clear()
                var currentMonth = formatter.format(date)[0].toString() + formatter.format(date)[1].toString()

                val index : Int = 7
                networkService = ApplicationController.instance.networkService
                var halmateScheduleInfoResponse  = networkService.getHalmateSchedule(index.toString())
                halmateScheduleInfoResponse.enqueue(object : retrofit2.Callback<HalmateScheuleInfoResponse> {
                    override fun onFailure(call: Call<HalmateScheuleInfoResponse>?, t: Throwable?) {
                        Log.e("통신실패", t.toString())
                    }

                    override fun onResponse(call: Call<HalmateScheuleInfoResponse>?, response: Response<HalmateScheuleInfoResponse>?) {
                        var size : Int = response!!.body().data.size
                        Log.e("0","흠")

                        var i = 0
                        while(true){
                            if(i == size){
                                break
                            }
                            else{
                                if(response.body().data[i].month == currentMonth){
                                    monthIndex = i
                                    var size2 = response.body().data[i].mon_sch.size
                                    for(j in 0 until size2){
                                        var date = response.body().data[i].mon_sch[j].date
                                        date = date[8].toString() + date[9].toString()
                                        currentDay.add(j,date.toInt())
                                        Log.e("day", currentDay[j].toString())
                                    }
                                }
                            }
                            i++
                        }



                        Log.e("통신성공",response!!.body().message)
                    }


                })


                calendar_month.setText(formatter.format(date))

                Handler().postDelayed({
                    val cal = Calendar.getInstance()
                    val dates = (1..31).filter { isScheduled(it, currentDay) == true }.map {
                        cal.apply {
                            set(view.month.year + 1900, view.month.month, it)
                        }.time
                    }
                    val map = mutableMapOf<Date, Collection<Accent>>().apply {
                        dates.forEach { date ->
                            val accents = (0..0).map { DotAccent(10f, key = "${formatter.format(date)}-$it") }
                            put(date, accents)
                        }
                    }
                    view.setAccents(map)
                }, 1000)

//                Log.i("MainActivity", "onMonthSelected: date = $date")
            }
        })
        calendarView.apply{
            monthFrom = Calendar.getInstance().apply { set(Calendar.MONTH,0) }.time
            monthTo = Calendar.getInstance().apply { set(Calendar.MONTH,11) }.time
            monthCurrent = Calendar.getInstance().time



        }


    }

    fun isScheduled(it : Int, dates : ArrayList<Int>) : Boolean {
        val i : Int = dates.size
        for (j in 0 until i){
            if(dates[j]==it){
                return true
            }
        }
        return false
    }
}