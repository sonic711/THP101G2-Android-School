package com.example.thp101g2_android_school.manage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.ManageMemberItemViewBinding
import com.example.thp101g2_android_school.manage.model.Members
import com.example.thp101g2_android_school.manage.model.SelectMemberBean
import com.example.thp101g2_android_school.manage.model.TeaApplyBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageMemberViewModel

class ManageMemberAdapter(private var members:List<SelectMemberBean>):
    RecyclerView.Adapter<ManageMemberAdapter.MemberViewHolder>() {

        fun updateMembers(members: List<SelectMemberBean>){
            this.members = members
            notifyDataSetChanged()
        }

    class  MemberViewHolder(val itemViewBinding: ManageMemberItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
     val itemViewBinding = ManageMemberItemViewBinding.inflate(
         LayoutInflater.from(parent.context), parent,false)
        itemViewBinding.viewModel = ManageMemberViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return  MemberViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = members[position]
        with(holder) {
            itemViewBinding.viewModel?.memberuse?.value = member
            val bundle = Bundle()
            bundle.putSerializable("member" , member)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_manageMemberFragment_to_manageMemberDetailFragment, bundle)
            }
        }
    }
}

