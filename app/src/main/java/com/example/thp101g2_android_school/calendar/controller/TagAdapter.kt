package com.example.thp101g2_android_school.calendar.controller

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.calendar.model.Tag
import com.example.thp101g2_android_school.calendar.viewModel.AddScheduleViewModel
import com.example.thp101g2_android_school.databinding.CalTagItemviewBinding

class TagAdapter(private var tags: MutableList<Tag>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<TagAdapter.TagViewHolder>(){

    private val myTag = "TAG_${javaClass.simpleName}"

    class TagViewHolder(val itemViewBinding: CalTagItemviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return tags.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val itemViewBinding = CalTagItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        itemViewBinding.viewModel = AddScheduleViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return TagViewHolder(itemViewBinding)
    }


    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val tag = tags[position]

        with (holder) {
            itemViewBinding.viewModel?.tag?.value = tag

            itemViewBinding.llTag.setOnClickListener {
                listener.onItemClick(tag.tagId, tag.definedColname)
            }
        }
    }

    fun updateTags(tags: MutableList<Tag>) {
        this.tags = tags
        notifyDataSetChanged()
    }

}

interface OnItemClickListener {
    fun onItemClick(tagId: Int?, tagName: String)
}