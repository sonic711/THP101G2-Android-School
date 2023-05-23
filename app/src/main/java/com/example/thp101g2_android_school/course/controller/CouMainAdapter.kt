package com.example.thp101g2_android_school.course.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.course.model.Course
import com.example.thp101g2_android_school.course.viewmodel.CouMainDetailViewModel
import com.example.thp101g2_android_school.databinding.CouCourseItemViewBinding

class CouMainAdapter(private var courses: List<Course>) :
    RecyclerView.Adapter<CouMainAdapter.CourseViewHolder>() {
    fun updateCourses(courses: List<Course>) {
        this.courses = courses
        notifyDataSetChanged()
    }

    class CourseViewHolder(val itemViewBinding: CouCourseItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemViewBinding = CouCourseItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = CouMainDetailViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CourseViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        with(holder) {
            itemViewBinding.viewModel?.course?.value = course
            val bundle = Bundle()
            bundle.putSerializable("course", course)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_couMainFragment_to_couCourseDetailFragment, bundle)
            }
        }
    }
}
