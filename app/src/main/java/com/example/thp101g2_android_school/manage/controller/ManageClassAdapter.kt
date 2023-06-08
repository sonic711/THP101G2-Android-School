package com.example.thp101g2_android_school.manage.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.databinding.ClassItemViewBinding
import com.example.thp101g2_android_school.manage.model.CourseReportBean
import com.example.thp101g2_android_school.manage.viewmodel.ManageClassViewModel

/**
 * 班級列表所需的Adapter
 */
class ManageClassAdapter(private var classes: List<CourseReportBean>) :
    RecyclerView.Adapter<ManageClassAdapter.ClassViewHolder>() {

    /**
     * 更新班級列表內容
     * @param classes 新的班級列表
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateClasses(classes: List<CourseReportBean>) {
        this.classes = classes
        notifyDataSetChanged()
    }

    class ClassViewHolder(val itemViewBinding: ClassItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return classes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val itemViewBinding = ClassItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        itemViewBinding.viewModel = ManageClassViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ClassViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val theClass = classes[position]
        with(holder) {
            // 將欲顯示的class物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.classo?.value = theClass
            val bundle = Bundle()
            bundle.putSerializable("class", theClass)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_manageClassesFragment_to_manageClassDetailFragment, bundle)
            }
        }
    }
}