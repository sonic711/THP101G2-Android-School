package com.example.thp101g2_android_school.calendar.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.calendar.model.MemSchedule
import com.example.thp101g2_android_school.calendar.viewModel.MemScheduleViewModel
import com.example.thp101g2_android_school.databinding.CalScheduleItemviewBinding
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat

class ScheduleAdapter(private var schedules: MutableList<MemSchedule>) :
    RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    private val myTag = "TAG_${javaClass.simpleName}"

    fun updateSchedules(schedules: MutableList<MemSchedule>) {
        this.schedules = schedules
        notifyDataSetChanged()
    }

    class ScheduleViewHolder(val itemViewBinding: CalScheduleItemviewBinding) :
            RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return schedules.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val itemViewBinding = CalScheduleItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = MemScheduleViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ScheduleViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = schedules[position]
        Log.d(myTag, "scheduleId: ${schedule.scheduleId}")
        with (holder) {
            itemViewBinding.viewModel?.schedule?.value = schedule
            val (year, month, day) = dateToString(holder)
            itemViewBinding.viewModel?.month?.value = month
            itemViewBinding.viewModel?.day?.value = day
            itemViewBinding.viewModel?.time?.value = combineTime(holder)
            Log.d(myTag, "scheduleMonth: ${schedule.date}")
            Log.d(myTag, "scheduleTime: ${schedule.startTime}")
            val bundle = Bundle()
            bundle.putSerializable("schedule", schedule)
            // FIXME 點擊 itemView 後將bundle帶到編輯畫面
        }
    }

    fun dateToString(holder: ScheduleViewHolder): Triple<String?, String?, String?> {
        val SDF = SimpleDateFormat("yyyy/MM/dd")
        val date = holder.itemViewBinding.viewModel?.schedule?.value?.date?.let { SDF.format(it) }
        val dateArray = date?.split("/")?.toTypedArray()
        val year = dateArray?.get(0)
        val month = convertToEnglishMonth(dateArray?.get(1) ?: "")
        val day = dateArray?.get(2)

        return Triple(year, month, day)
    }

    private fun convertToEnglishMonth(month: String): String {
        val monthNumber = month.toIntOrNull()
        if (monthNumber != null && monthNumber in 1..12) {
            val symbols = DateFormatSymbols()
            val months = symbols.shortMonths
            return months[monthNumber - 1].toUpperCase()
        }
        return ""
    }

    private fun combineTime(holder: ScheduleViewHolder): String {
        val startTime = holder.itemViewBinding.viewModel?.schedule?.value?.startTime?.toString()?.substring(0,5)
        val endTime = holder.itemViewBinding.viewModel?.schedule?.value?.endTime?.toString()?.substring(0,5)

        return "$startTime - $endTime"

    }

}