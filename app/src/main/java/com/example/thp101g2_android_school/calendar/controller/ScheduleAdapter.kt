package com.example.thp101g2_android_school.calendar.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.calendar.model.Schedule
import com.example.thp101g2_android_school.calendar.viewModel.CalScheduleItemViewModel
import com.example.thp101g2_android_school.calendar.viewModel.SchedulesViewModel
import com.example.thp101g2_android_school.databinding.CalScheduleItemviewBinding

class ScheduleAdapter(private var schedules: MutableList<Schedule>) :
    RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    fun updateSchedules(schedules: MutableList<Schedule>) {
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
        itemViewBinding.viewModel = CalScheduleItemViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ScheduleViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = schedules[position]
        with(holder) {
            itemViewBinding.viewModel?.schedule?.value = schedule
            val bundle = Bundle()
            bundle.putSerializable("schedule", schedule)
            // FIXME 點擊 itemView 後將bundle帶到編輯畫面
        }
    }

}