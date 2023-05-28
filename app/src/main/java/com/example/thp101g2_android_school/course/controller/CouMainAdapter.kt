package com.example.thp101g2_android_school.course.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.course.model.Courses
import com.example.thp101g2_android_school.course.viewmodel.CouMainDetailViewModel
import com.example.thp101g2_android_school.databinding.CouCourseItemViewBinding

class CouMainAdapter(private var courses: List<Courses>) :
    RecyclerView.Adapter<CouMainAdapter.CourseViewHolder>() {
    fun updateCourses(courses: List<Courses>) {
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
        val courses = courses[position]
        with(holder) {
            itemViewBinding.viewModel?.course?.value = courses

            if (courses.image != null) {
                val img = byteArrayToBitmap(courses.image!!)
                itemViewBinding.ivCourse.setImageBitmap(img)
            }else{
                itemViewBinding.ivCourse.setBackgroundResource(R.drawable.com_user)
            }
            val bundle = Bundle()
            bundle.putSerializable("course", courses)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_couMainFragment_to_couCourseDetailFragment, bundle)
            }
        }
    }
}
