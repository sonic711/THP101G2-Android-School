package com.example.thp101g2_android_school.community.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.community.model.Reply
import com.example.thp101g2_android_school.community.viewmodel.ComMainViewModel
import com.example.thp101g2_android_school.community.viewmodel.ReplyViewModel
import com.example.thp101g2_android_school.databinding.ComMainItemviewBinding
import com.example.thp101g2_android_school.databinding.ComReplyItemviewBinding

class ReplyAdapter(private val replyList: List<Reply>) :
    RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder>() {

    class ReplyViewHolder(val itemViewBinding: ComReplyItemviewBinding) :

        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return replyList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyViewHolder {
        val itemViewBinding = ComReplyItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ReplyViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()

        return ReplyViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ReplyViewHolder, position: Int) {
        val reply = replyList[position]
        with(holder){
            itemViewBinding.viewModel?.reply?.value = reply
            if (reply.profilePhoto != null) {
                val img = byteArrayToBitmap(reply.profilePhoto!!)
                itemViewBinding.imageView.setImageBitmap(img)
            }else{
                itemViewBinding.imageView.setBackgroundResource(R.drawable.com_user)
            }
        }

    }
}