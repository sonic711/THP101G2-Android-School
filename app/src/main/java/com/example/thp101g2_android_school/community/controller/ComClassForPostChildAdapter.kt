package com.example.thp101g2_android_school.community.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.community.model.ChildItem
import com.example.thp101g2_android_school.community.viewmodel.ClassChildViewModel
import com.example.thp101g2_android_school.databinding.ComClassChildItemBinding

class ComClassForPostChildAdapter(private val childList: List<ChildItem>) :
    RecyclerView.Adapter<ComClassForPostChildAdapter.ChildViewHolder>() {

    class ChildViewHolder(val itemViewBinding: ComClassChildItemBinding) :

        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComClassForPostChildAdapter.ChildViewHolder {

        val itemViewBinding = ComClassChildItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ClassChildViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()


        return ChildViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ComClassForPostChildAdapter.ChildViewHolder, position: Int) {
        val childs = childList[position]
        with(holder) {
            itemViewBinding.toggleButton.isVisible = false
            itemViewBinding.viewModel?.child?.value = childs
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("child", childs.childName) // 将数据添加到Bundle中，使用合适的键值对

                val navController = Navigation.findNavController(it)
                navController.previousBackStackEntry?.savedStateHandle?.set("bundle", bundle)
                navController.popBackStack()

            }
        }
    }

    override fun getItemCount(): Int {
        return childList.size
    }
}