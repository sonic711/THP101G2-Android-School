package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.MemBlockItemviewBinding
import com.example.thp101g2_android_school.member.model.BlockedUser
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.member.viewModel.MemBlockViewModel

class BlockedUserAdapter(private var blockedUsers: MutableList<BlockedUser>): RecyclerView.Adapter<BlockedUserAdapter.BlockedUserViewHolder>() {

    class BlockedUserViewHolder(val itemViewBinding: MemBlockItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return blockedUsers.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockedUserViewHolder {
        val itemViewBinding = MemBlockItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = MemBlockViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return BlockedUserViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: BlockedUserViewHolder, position: Int) {
        val blockedUser = blockedUsers[position]

        with(holder) {
            itemViewBinding.viewModel?.blockedUser?.value = blockedUser
            val bundle = Bundle()
            bundle.putSerializable("member", changeToMember(holder, blockedUser))
            itemView.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.memOthersHomeFragment, bundle)
            }
            itemViewBinding.btUnblock.setOnClickListener {
                AlertDialog.Builder(holder.itemView.context)
                    .setMessage("確定要解除此用戶的封鎖嗎？")
                    .setPositiveButton("確定") { dialog, which ->
                        val id = blockedUser.memberBlockingId
                        holder.itemViewBinding.viewModel?.unblock(id)
                        blockedUsers.removeAt(position)
                        notifyItemRemoved(adapterPosition)
                        notifyDataSetChanged()
                    }
                    .setNegativeButton("取消", null)
                    .show()
            }
        }
    }

    fun updateBlockedUsers(blockedUsers: MutableList<BlockedUser>) {
        this.blockedUsers = blockedUsers
        notifyDataSetChanged()
    }

    private fun changeToMember(holder: BlockedUserAdapter.BlockedUserViewHolder, blockedUser: BlockedUser): Member? {
        holder.itemViewBinding.viewModel?.member?.value?.let { member ->
            member.memberNo = blockedUser.memberBlocking
            member.userId = blockedUser.userId
            member.nickname = blockedUser.nickname
            member.memberIdentity = blockedUser.memberIdentity
            member.profilePhoto64 = blockedUser.profilePhoto64
            member.coverPicture64 = blockedUser.coverPicture64
            member.memberStatus = blockedUser.memberStatus
            member.introduction = blockedUser.introduction
            return member
        }
        return null
    }
}