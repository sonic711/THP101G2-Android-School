package com.example.thp101g2_android_school.course.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.course.model.Comment
import com.example.thp101g2_android_school.course.viewmodel.CouCommentDetailViewModel
import com.example.thp101g2_android_school.databinding.CouCommenyItemViewBinding

class CouCommentAdapter (private var comments: List<Comment>):
        RecyclerView.Adapter<CouCommentAdapter.CommentViewHolder>() {
    fun updateComments(comments: List<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    class CommentViewHolder(val itemViewBinding: CouCommenyItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemViewBinding = CouCommenyItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = CouCommentDetailViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CommentViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        with(holder) {
            itemViewBinding.viewModel?.comment?.value = comment
            if (comment.image != null){
                val img = byteArrayToBitmap(comment.image!!)
                itemViewBinding.ivCourse.setImageBitmap(img)
            }else{
                itemViewBinding.ivCourse.setBackgroundResource(R.drawable.com_user)
            }

            val bundle = Bundle()
            bundle.putSerializable("comment", comment)
        }
    }
}
