package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.databinding.MemFanItemviewBinding
import com.example.thp101g2_android_school.databinding.MemFollowerItemviewBinding
import com.example.thp101g2_android_school.databinding.MemOthersFanItemviewBinding
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.member.viewModel.MemOthersHomeViewModel
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel
import com.google.gson.JsonObject

class OthersFanAdapter(private var fans: MutableList<Follower>) : RecyclerView.Adapter<OthersFanAdapter.FanViewHolder>() {

    class FanViewHolder(val itemViewBinding: MemOthersFanItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return fans.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FanViewHolder {
        val itemViewBinding = MemOthersFanItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = MemOthersHomeViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return FanViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: FanViewHolder, position: Int) {
        val fan = fans[position]
        with(holder) {
            itemViewBinding.viewModel?.fan?.value = fan
            val bundle = Bundle()
            bundle.putSerializable("member", changeToMember(holder, fan))
            itemView.setOnClickListener {
                // 如果目前的會員頁面是登入的會員，則跳到自己的主頁
                if (fan.memberNo == itemViewBinding.viewModel?.getCurrentMember()?.memberNo) {
                    Navigation.findNavController(it).navigate(R.id.memMainFragment)
                } else {
                    Navigation.findNavController(it).navigate(R.id.memOthersHomeFragment, bundle)
                }
            }

            // 如果此會員的粉絲中有目前登入的會員，則追蹤按鈕隱藏
            if (fan.memberNo == itemViewBinding.viewModel?.getCurrentMember()?.memberNo) {
                itemViewBinding.btFollow.visibility = View.INVISIBLE
            }

            checkFollowBack(holder, fan)

            itemViewBinding.btFollow.setOnClickListener {
                // 點下去的狀態。btFollow.isChecked = 追蹤中 -> addFollower
                if (itemViewBinding.btFollow.isChecked) {
                    holder.itemViewBinding.viewModel?.addFollower(changeToMember(holder, fan))
                } else {
                    val memberNo = fan.memberNo
                    holder.itemViewBinding.viewModel?.unfollow(memberNo)
                }
                checkFollowBack(holder, fan)
            }
        }
    }

    fun updateFans(fans: MutableList<Follower>) {
        this.fans = fans
        notifyDataSetChanged()
    }

    private fun changeToMember(holder: FanViewHolder, fan: Follower): Member? {
        holder.itemViewBinding.viewModel?.member?.value?.let { member ->
            member.memberNo = fan.memberNo
            member.userId = fan.userId
            member.nickname = fan.nickname
            member.memberIdentity = fan.memberIdentity
            member.profilePhoto64 = fan.profilePhoto64
            member.coverPicture64 = fan.coverPicture64
            member.memberStatus = fan.memberStatus
            member.introduction = fan.introduction
            return member
        }
        return null
    }

    private fun checkFollowBack(holder: FanViewHolder, fan: Follower) {
        val list = holder.itemViewBinding.viewModel?.searchFollowBackList()
        list?.let { list ->
            for (follower in list) {
                // follower.memberFollowing 是 追蹤該會員的粉絲的會員編號
                if (follower.memberFollowing == fan.memberNo) {
                    holder.itemViewBinding.btFollow.isChecked = true
                    break
                }
            }
        }
    }
}