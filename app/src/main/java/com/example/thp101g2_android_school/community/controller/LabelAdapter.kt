package com.example.thp101g2_android_school.community.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.community.viewmodel.LabelViewModel
import com.example.thp101g2_android_school.databinding.ComLabelItemviewBinding

class LabelAdapter(private var labels: List<Label>) :
    RecyclerView.Adapter<LabelAdapter.LabelViewHolder>() {

    class LabelViewHolder(val itemViewBinding: ComLabelItemviewBinding) :

        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        val itemViewBinding = ComLabelItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = LabelViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()

        return LabelViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        val label = labels[position]
        with(holder) {

            itemViewBinding.viewModel?.label?.value = label
            itemView.setOnClickListener {
                // TODO 按下CardView後搜尋該標籤內容
            }
        }
    }

    override fun getItemCount(): Int {
        return labels.size
    }

}