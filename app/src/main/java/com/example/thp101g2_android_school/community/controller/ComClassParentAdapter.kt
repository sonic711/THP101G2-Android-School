package com.example.thp101g2_android_school.community.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.community.model.FollowClassBean
import com.example.thp101g2_android_school.community.model.ParentItem
import com.example.thp101g2_android_school.community.viewmodel.ClassParentViewModel
import com.example.thp101g2_android_school.community.viewmodel.ComClassViewModel
import com.example.thp101g2_android_school.databinding.ComClassParentItemBinding

class ComClassParentAdapter(private val parentList: List<ParentItem>, private val followList: List<FollowClassBean>) :
    RecyclerView.Adapter<ComClassParentAdapter.ParentViewHolder>() {

    class ParentViewHolder(val itemViewBinding: ComClassParentItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val itemViewBinding = ComClassParentItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ClassParentViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()

        // STEP06-3.回傳給onBindViewHolder用
        return ParentViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val parent = parentList[position]

        with(holder.itemViewBinding) {
            viewModel?.parent?.value = parent
            childRecyclerView.layoutManager = GridLayoutManager(holder.itemView.context, 1)
            childRecyclerView.adapter = ComClassChildAdapter(parent.childs, followList)
        }
    }
    override fun getItemCount(): Int {
        return parentList.size
    }
}