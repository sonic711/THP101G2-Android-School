package com.example.thp101g2_android_school.course.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.course.model.FavCourse
import com.example.thp101g2_android_school.course.viewmodel.CouFavoriteDetailViewModel
import com.example.thp101g2_android_school.databinding.CouFavCourseItemViewBinding


class CouFavoriteAdapter(private var favcourses: List<FavCourse>) :
    RecyclerView.Adapter<CouFavoriteAdapter.FavcourseViewHolder>() {
    fun updateFavcourses(favcourses: List<FavCourse>) {
        this.favcourses = favcourses
        notifyDataSetChanged()
    }

    class FavcourseViewHolder(val itemViewBinding: CouFavCourseItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return favcourses.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavcourseViewHolder {
        val itemViewBinding = CouFavCourseItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = CouFavoriteDetailViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return FavcourseViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: FavcourseViewHolder, position: Int) {
        val favcourse = favcourses[position]
        with(holder) {
            itemViewBinding.viewModel?.favcourse?.value = favcourse
            val bundle = Bundle()
            bundle.putSerializable("favcourse", favcourse)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_couFavoriteFragment_to_couClassroomFragment)
            }
        }
    }
}