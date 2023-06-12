package com.example.thp101g2_android_school.course.controller

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.Chapter
import com.example.thp101g2_android_school.course.viewmodel.CouClassroomDetailViewModel
import com.example.thp101g2_android_school.course.viewmodel.CouFavoriteDetailViewModel
import com.example.thp101g2_android_school.databinding.CouClassroomItemViewBinding
import com.example.thp101g2_android_school.databinding.CouFavCourseItemViewBinding
import com.google.gson.JsonObject

class CouClassroomAdapter (private var roomcourses: List<Chapter>) :
    RecyclerView.Adapter<CouClassroomAdapter.roomCoursesViewHolder>() {

    fun updateroomCourses(roomcourses: List<Chapter>) {
        this.roomcourses = roomcourses
        notifyDataSetChanged()
    }

    class roomCoursesViewHolder(val itemViewBinding: CouClassroomItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)
        var videoUrl: String? = null


    override fun getItemCount(): Int {
        return roomcourses.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): roomCoursesViewHolder {
        val itemViewBinding = CouClassroomItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = CouClassroomDetailViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return roomCoursesViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: roomCoursesViewHolder, position: Int) {
        val roomcourse = roomcourses[position]
        with(holder) {
            itemViewBinding.viewModel?.roomcourse?.value = roomcourse
            videoUrl = roomcourse.video

            itemView.setOnClickListener {
                roomcourse.video?.let {
                    itemView.findFragment<CouClassroomFragment>().binding.videoView.setVideoURI(Uri.parse(it))
                }
            }

//            itemView.setOnClickListener {
//                val videoUrl = videoUrl
//                if (!videoUrl.isNullOrEmpty()) {
//                    val fragment = itemView.findFragment<CouClassroomFragment>()
//                    fragment?.importVideoUrl(videoUrl)
//                }
//            }
        }
    }
}