package com.example.thp101g2_android_school

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.community.model.Label
import kotlinx.coroutines.sync.Mutex

class ActivityViewModel : ViewModel() {
    // 社群用
    // 把該標籤放到這個集合中，並顯示
    val newLabels by lazy { LinkedHashSet<Label>() }
    val viewModelLabels: MutableLiveData<LinkedHashSet<Label>> by lazy { MutableLiveData<LinkedHashSet<Label>>() }

    /**
     *  社群用
     *  點擊標籤之後，把該標籤放到新的集合中
     *  @param str 點擊到的標籤名稱
     * */
    fun getLabelToInsert(label: Label) {
        if (newLabels.size >= 3) {
            val firstEle = newLabels.first()
            newLabels.remove(firstEle)
        }
        newLabels.add(label)
        println(newLabels)
    }
}