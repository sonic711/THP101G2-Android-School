package com.example.thp101g2_android_school.calendar.controller

import com.example.thp101g2_android_school.databinding.CalEditTagItemviewBinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.calendar.model.Tag
import com.example.thp101g2_android_school.calendar.viewModel.AddScheduleViewModel

class EditTagAdapter(private var tags: MutableList<Tag>) :
    RecyclerView.Adapter<EditTagAdapter.EditTagViewHolder>(){

    var newTagsList = mutableListOf<Tag>()
    private val myTag = "TAG_${javaClass.simpleName}"

    class EditTagViewHolder(val itemViewBinding: CalEditTagItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return tags.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTagViewHolder {
        val itemViewBinding = CalEditTagItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        itemViewBinding.viewModel = AddScheduleViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return EditTagViewHolder(itemViewBinding)
    }


    override fun onBindViewHolder(holder: EditTagViewHolder, position: Int) {
        val tag = tags[position]

        with (holder) {
            itemViewBinding.viewModel?.tag?.value = tag
            newTagsList.add(tag)
        }
    }

    fun updateEditTags(tags: MutableList<Tag>) {
        this.tags = tags
        notifyDataSetChanged()
    }

    fun getEditTags() : List<Tag> {
        return newTagsList
    }


}