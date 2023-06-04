package com.example.thp101g2_android_school.member.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.databinding.MemFollowerItemviewBinding
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel

class FollowerAdapter(private var followers: MutableList<Follower>) : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {

    class FollowerViewHolder(val itemViewBinding: MemFollowerItemviewBinding) :
            RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return followers.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val itemViewBinding = MemFollowerItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = MemberViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return FollowerViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val follower = followers[position] as Follower

        with(holder) {
            itemViewBinding.viewModel?.follower?.value = follower
        }
    }

    fun updateFollowers(followers: MutableList<Follower>) {
        this.followers = followers
        notifyDataSetChanged()
    }

}