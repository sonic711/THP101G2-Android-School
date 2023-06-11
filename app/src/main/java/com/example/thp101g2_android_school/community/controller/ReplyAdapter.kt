package com.example.thp101g2_android_school.community.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.ActivityViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.byteArrayToBitmap
import com.example.thp101g2_android_school.community.model.ReplyAndLike
import com.example.thp101g2_android_school.community.viewmodel.ReplyViewModel
import com.example.thp101g2_android_school.databinding.ComReplyItemviewBinding

class ReplyAdapter(
    private var replyList: List<ReplyAndLike>,
    private val likeList: List<ReplyAndLike>?,
    private val activityViewModel: ActivityViewModel
) :
    RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder>() {

    class ReplyViewHolder(val itemViewBinding: ComReplyItemviewBinding) :

        RecyclerView.ViewHolder(itemViewBinding.root)

    /**
     * 更新回應列表內容
     * @param reply 新的回應列表
     */
    fun updateReply(reply: List<ReplyAndLike>) {
        this.replyList = reply
        notifyDataSetChanged()
    }

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
        val memberObj = activityViewModel.memberObj.value

        with(holder.itemViewBinding) {
            viewModel?.reply?.value = reply
            if (reply.profilePhoto != null) {
                val img = byteArrayToBitmap(reply.profilePhoto!!)
                imageView.setImageBitmap(img)
            } else {
                imageView.setBackgroundResource(R.drawable.com_user)
            }
            // 從資料庫取出來該文章的所有喜歡來判斷有沒有喜歡過
            likeList?.let {
                for (liked in it) {
                    // 找到屬於該MemberNo的喜歡並且 喜歡的編號 = 該回覆的編號
                    if (liked.likedMemberNo == memberObj?.memberNo.toString() && (liked.likedReplyId == reply.comReplyId)) {
                        tbLike.isChecked = true
                        break
                    } else {
                        tbLike.isChecked = false
                    }
                }
            }
            // 如果已經有這一個喜歡紀錄 就刪除 沒有這個紀錄就新增 ， 並同時更改愛心總數
            var currentLikeCount = viewModel?.reply?.value?.likeCounts
            tbLike.setOnClickListener {
                if (tbLike.isChecked) {
                    memberObj?.let { member ->
                        currentLikeCount = currentLikeCount!! + 1
                        tvLikesCount.text = currentLikeCount.toString()
                        viewModel?.addLike(member)
                    }
                } else {
                    // 已追蹤，就刪除
                    memberObj?.let { member ->
                        currentLikeCount = currentLikeCount!! - 1
                        tvLikesCount.text = currentLikeCount.toString()
                        viewModel?.cancelLike(member)
                    }
                }
            }
        }
    }
}