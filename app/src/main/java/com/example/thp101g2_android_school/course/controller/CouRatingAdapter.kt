package com.example.thp101g2_android_school.course.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.course.model.Comment
import com.example.thp101g2_android_school.course.viewmodel.CouRatingDetailViewModel
import com.example.thp101g2_android_school.databinding.CouRatingItemViewBinding

class CouRatingAdapter (private var ratings: List<Comment>) :
        RecyclerView.Adapter<CouRatingAdapter.RatingViewHolder>(){
            fun updateRatings(ratings: List<Comment>) {
                this.ratings = ratings
                notifyDataSetChanged()
            }
    class RatingViewHolder(val itemViewBinding: CouRatingItemViewBinding) :
            RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return ratings.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val itemViewBinding = CouRatingItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = CouRatingDetailViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return RatingViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val ratings = ratings[position]
        with(holder){
            itemViewBinding.viewModel?.rating?.value = ratings
            itemViewBinding.lifecycleOwner = holder.itemView.findViewTreeLifecycleOwner()
            if (ratings.image != null) {
                val img = byteArrayToBitmap(ratings.image!!)
                itemViewBinding.ivCourse.setImageBitmap(img)
            }else{
                itemViewBinding.ivCourse.setBackgroundResource(R.drawable.com_user)
            }
        }
    }
        }