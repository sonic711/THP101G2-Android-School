package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.MemFanItemviewBinding
import com.example.thp101g2_android_school.databinding.MemFollowerItemviewBinding
import com.example.thp101g2_android_school.databinding.MemOthersFanItemviewBinding
import com.example.thp101g2_android_school.databinding.MemOthersFollowerItemviewBinding
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.member.viewModel.MemOthersHomeViewModel
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel
import com.google.gson.JsonObject

class OthersFollowerAdapter(private var followers: MutableList<Follower>) : RecyclerView.Adapter<OthersFollowerAdapter.FollowerViewHolder>() {

    class FollowerViewHolder(val itemViewBinding: MemOthersFollowerItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return followers.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val itemViewBinding = MemOthersFollowerItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = MemOthersHomeViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return FollowerViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val follower = followers[position]
        with(holder) {
            itemViewBinding.viewModel?.follower?.value = follower
            val bundle = Bundle()
            bundle.putSerializable("member", changeToMember(holder, follower))
            itemView.setOnClickListener {
                // 如果目前的會員頁面是登入的會員，則跳到自己的主頁
                if (follower.memberFollowing == itemViewBinding.viewModel?.getCurrentMember()?.memberNo) {
                    Navigation.findNavController(it).navigate(R.id.memMainFragment)
                } else {
                    Navigation.findNavController(it).navigate(R.id.memOthersHomeFragment, bundle)
                }
            }

            // 如果此會員追蹤的會員中有目前登入的會員，則追蹤按鈕隱藏
            if (follower.memberFollowing == itemViewBinding.viewModel?.getCurrentMember()?.memberNo) {
                itemViewBinding.btFollow.visibility = View.INVISIBLE
            }

            checkFollowBack(holder, follower)

            itemViewBinding.btFollow.setOnClickListener {
                // 點下去的狀態。btFollow.isChecked = 追蹤中 -> addFollower
                if (itemViewBinding.btFollow.isChecked) {
                    holder.itemViewBinding.viewModel?.addFollower(changeToMember(holder, follower))
                } else {
                    val memberNo = follower.memberFollowing
                    holder.itemViewBinding.viewModel?.unfollow(memberNo)
                }
                checkFollowBack(holder, follower)
            }
        }
    }

    fun updateFollowers(followers: MutableList<Follower>) {
        this.followers = followers
        notifyDataSetChanged()
    }

    private fun changeToMember(holder: FollowerViewHolder, follower: Follower): Member? {
        holder.itemViewBinding.viewModel?.member?.value?.let { member ->
            member.memberNo = follower.memberFollowing
            member.userId = follower.userId
            member.nickname = follower.nickname
            member.memberIdentity = follower.memberIdentity
            member.profilePhoto64 = follower.profilePhoto64
            member.coverPicture64 = follower.coverPicture64
            member.memberStatus = follower.memberStatus
            member.introduction = follower.introduction
            return member
        }
        return null
    }

    // FIXME
    private fun checkFollowBack(holder: FollowerViewHolder, follower: Follower) {
        val list = holder.itemViewBinding.viewModel?.searchFollowBackList()
        list?.let { list ->
            for (following in list) {
                // follower.memberFollowing 是 追蹤該會員的粉絲的會員編號
                if (following.memberFollowing == follower.memberFollowing) {
                    holder.itemViewBinding.btFollow.isChecked = true
                    break
                }
            }
        }
    }
}