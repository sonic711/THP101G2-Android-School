package com.example.thp101g2_android_school.member.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.databinding.MemFollowerItemviewBinding
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.viewModel.MemberViewModel
import com.google.gson.JsonObject

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
            val bundle = Bundle()
            bundle.putSerializable("follower", follower)
            itemView.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.memMainFragment, bundle)
            }
            itemViewBinding.btDelete.setOnClickListener {

                // FIXME 發請求給後端刪除
                AlertDialog.Builder(holder.itemView.context)
                    .setMessage("確定要刪除此用戶嗎？")
                    .setPositiveButton("確定") { dialog, which ->
                        val id = follower.memberFollowingId
                        val respbody = requestTask<JsonObject>(
                            "http://10.0.2.2:8080/THP101G2-WebServer-School/member/follow/followers/$id",
                            "DELETE"
                        )
                        followers.removeAt(position)
                        notifyItemRemoved(adapterPosition)
                        notifyDataSetChanged()
                    }
                    .setNegativeButton("取消", null)
                    .show()
            }
        }
    }

    fun updateFollowers(followers: MutableList<Follower>) {
        this.followers = followers
        notifyDataSetChanged()
    }

}