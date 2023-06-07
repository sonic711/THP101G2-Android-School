package com.example.thp101g2_android_school.course.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.course.model.Chapter
import com.example.thp101g2_android_school.course.viewmodel.CouClassroomDetailViewModel
import com.example.thp101g2_android_school.course.viewmodel.CouFavoriteDetailViewModel
import com.example.thp101g2_android_school.databinding.CouClassroomItemViewBinding
import com.example.thp101g2_android_school.databinding.CouFavCourseItemViewBinding

class CouClassroomAdapter (private var roomcourses: List<Chapter>) :
    RecyclerView.Adapter<CouClassroomAdapter.roomCoursesViewHolder>() {
    fun updateroomCourses(roomcourses: List<Chapter>) {
        this.roomcourses = roomcourses
        notifyDataSetChanged()
    }

    class roomCoursesViewHolder(val itemViewBinding: CouClassroomItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return roomcourses.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouClassroomAdapter.roomCoursesViewHolder {
        val itemViewBinding = CouClassroomItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = CouClassroomDetailViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CouClassroomAdapter.roomCoursesViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: CouClassroomAdapter.roomCoursesViewHolder, position: Int) {
        val roomcourse = roomcourses[position]
        with(holder) {
            itemViewBinding.viewModel?.roomcourse?.value = roomcourse
            val bundle = Bundle()
            bundle.putSerializable("roomcourse", roomcourse)
            itemView.setOnClickListener {

            }
        }
    }
}