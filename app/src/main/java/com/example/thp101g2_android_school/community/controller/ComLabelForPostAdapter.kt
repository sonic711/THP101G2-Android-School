package com.example.thp101g2_android_school.community.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.community.viewmodel.LabelViewModel
import com.example.thp101g2_android_school.databinding.ComLabelItemviewBinding

class ComLabelForPostAdapter(private var labels: List<Label>) :
    RecyclerView.Adapter<ComLabelForPostAdapter.LabelForPostViewHolder>() {

    class LabelForPostViewHolder(val itemViewBinding: ComLabelItemviewBinding) :

        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return labels.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelForPostViewHolder {
        val itemViewBinding = ComLabelItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = LabelViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()

        return ComLabelForPostAdapter.LabelForPostViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: LabelForPostViewHolder, position: Int) {
        val label = labels[position]
        with(holder) {
            itemViewBinding.viewModel?.label?.value = label
            itemViewBinding.cardView.setOnClickListener {
                println(label)
            }
        }
    }

}