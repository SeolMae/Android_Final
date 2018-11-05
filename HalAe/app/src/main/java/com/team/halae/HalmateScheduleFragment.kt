package com.team.halae

import android.app.usage.UsageEvents
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
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
import kotlinx.android.synthetic.main.fragment_halmate_schedule.*
import kotlinx.android.synthetic.main.fragment_halmate_schedule.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HalmateScheduleFragment : Fragment() {

    lateinit var calendarView: LightCalendarView

    private val formatter = SimpleDateFormat("MMMM yyyy", Locale.getDefault())


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

                startActivity(Intent(context, HalmateScheduleSelectActivity::class.java))
            }


            //map에 date만 통신후에 수정하기!
            //isScheduled 함수 짜서 isScheduled(it) 가 true 인 날짜에만 dotted!
            override fun onMonthSelected(date: Date, view: MonthView) {
//                val title = view.findViewById<TextView>(R.id.calendar_month)
//                title.text = formatter.format(date)
                calendar_month.setText(formatter.format(date))
                Handler().postDelayed({
                    val cal = Calendar.getInstance()
                    val dates = (1..31).filter { it % 2 == 0 }.map {
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
}