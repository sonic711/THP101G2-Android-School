package com.example.thp101g2_android_school.community.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.community.model.ChildItem
import com.example.thp101g2_android_school.community.viewmodel.ClassChildViewModel
import com.example.thp101g2_android_school.databinding.ComClassChildItemBinding

class ComClassChildAdapter(private val childList: List<ChildItem>) :
    RecyclerView.Adapter<ComClassChildAdapter.ChildViewHolder>() {

    class ChildViewHolder(val itemViewBinding: ComClassChildItemBinding) :

        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComClassChildAdapter.ChildViewHolder {

        val itemViewBinding = ComClassChildItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ClassChildViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()


        return ChildViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ComClassChildAdapter.ChildViewHolder, position: Int) {
        val childs = childList[position]
        with(holder) {

            itemViewBinding.viewModel?.child?.value = childs
            itemView.setOnClickListener{
                //TODO 直接轉到對應文章分類的頁面
            }
            itemViewBinding.toggleButton.setOnClickListener{
                //TODO 這邊還要先判斷有無追蹤，更改顏色後送到資料庫
                if (itemViewBinding.toggleButton.isChecked){
                it.setBackgroundResource(R.drawable.togglebtn)}
                else {
                    it.setBackgroundResource(R.drawable.togglebtnfalse)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return childList.size
    }
}