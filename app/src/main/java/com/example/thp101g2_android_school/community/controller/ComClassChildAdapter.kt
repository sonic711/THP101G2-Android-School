package com.example.thp101g2_android_school.community.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.community.model.ChildItem
import com.example.thp101g2_android_school.community.model.FollowClassBean
import com.example.thp101g2_android_school.community.viewmodel.ClassChildViewModel
import com.example.thp101g2_android_school.databinding.ComClassChildItemBinding

class ComClassChildAdapter(private val childList: List<ChildItem>, private val followList: List<FollowClassBean>) :
    RecyclerView.Adapter<ComClassChildAdapter.ChildViewHolder>() {

    class ChildViewHolder(val itemViewBinding: ComClassChildItemBinding) :

        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {

        val itemViewBinding = ComClassChildItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = ClassChildViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()


        return ChildViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val childs = childList[position]
        with(holder) {
            with(itemViewBinding) {
                viewModel?.child?.value = childs
                // 從viewModel取出所有追蹤紀錄並比對追蹤狀況
                for (followed in followList) {
                    if (followed.comSecClassId == childs.comSecClassId) {
                        toggleButton.isChecked = true
                        break
                    } else {
                        toggleButton.isChecked = false
                    }
                }
            }
            itemView.setOnClickListener {
                //TODO 直接轉到對應文章分類的頁面

            }
            // 點擊追蹤按鈕後
            itemViewBinding.toggleButton.setOnClickListener {
                // 如果未追蹤，就新增一筆資料到資料庫
                if (itemViewBinding.toggleButton.isChecked) {
                    holder.itemViewBinding.viewModel?.addFollow()
                } else {
                    // 已追蹤，就刪除 該會員 該次分類編號的追蹤紀錄
                    holder.itemViewBinding.viewModel?.cancelFollow()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return childList.size
    }
}