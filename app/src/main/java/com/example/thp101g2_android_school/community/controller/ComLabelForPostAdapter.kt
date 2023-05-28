package com.example.thp101g2_android_school.community.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.ActivityViewModel
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.community.viewmodel.LabelViewModel
import com.example.thp101g2_android_school.databinding.ComLabelForPostItemviewBinding
import com.example.thp101g2_android_school.databinding.ComLabelItemviewBinding

class ComLabelForPostAdapter(private var labels: List<Label>, private var activityViewModel: ActivityViewModel) :
    RecyclerView.Adapter<ComLabelForPostAdapter.LabelForPostViewHolder>() {

    class LabelForPostViewHolder(val itemViewBinding: ComLabelForPostItemviewBinding) :

        RecyclerView.ViewHolder(itemViewBinding.root)

    /**
     * 更新發文標籤列表內容
     * @param labels 新的標籤列表
     */
    fun updateLabels(labels: List<Label>) {
        this.labels = labels
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return labels.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelForPostViewHolder {
        val itemViewBinding = ComLabelForPostItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = LabelViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()

        return LabelForPostViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: LabelForPostViewHolder, position: Int) {
        val label = labels[position]
        with(holder.itemViewBinding) {
            viewModel?.label?.value = label
            cardView.setOnClickListener {
                // 取得點擊的Label名稱，並存到activityViewModel中
                activityViewModel.getLabelToInsert(label)
            }
        }
    }
}