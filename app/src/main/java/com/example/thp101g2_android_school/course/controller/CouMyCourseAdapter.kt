package com.example.thp101g2_android_school.course.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.course.model.MyCourse
import com.example.thp101g2_android_school.course.viewmodel.CouMyCourseDetailViewModel
import com.example.thp101g2_android_school.databinding.CouMyCourseItemViewBinding


class CouMyCourseAdapter(private var mycourses: List<MyCourse>) :
    RecyclerView.Adapter<CouMyCourseAdapter.MycourseViewHolder>() {
    fun updateMycourses(mycourses: List<MyCourse>) {
        this.mycourses = mycourses
        notifyDataSetChanged()
    }

    class MycourseViewHolder(val itemViewBinding: CouMyCourseItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return mycourses.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MycourseViewHolder {
        val itemViewBinding = CouMyCourseItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = CouMyCourseDetailViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MycourseViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: MycourseViewHolder, position: Int) {
        val mycourse = mycourses[position]
        with(holder) {
            itemViewBinding.viewModel?.mycourse?.value = mycourse
            val bundle = Bundle()
            bundle.putSerializable("mycourse", mycourse)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_couMyCourseFragment_to_couClassroomFragment)
            }
        }
    }
}