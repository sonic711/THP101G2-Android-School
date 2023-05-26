package com.example.thp101g2_android_school

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.community.model.Label

class ActivityViewModel : ViewModel() {
    // 社群用
    // 把該標籤放到這個集合中，並顯示
    val newLabels by lazy { LinkedHashSet<Label>() }
    val labelList: MutableLiveData<LinkedHashSet<Label>> by lazy { MutableLiveData<LinkedHashSet<Label>>() }

    /**
     *  社群用
     *  點擊標籤之後，把該標籤放到新的集合中，超過第三個會把第一個加入的擠掉
     *  重複的話會直接移除該標籤
     *  @param label 點擊到的標籤
     * */
    fun getLabelToInsert(label: Label) {
        if (newLabels.contains(label)){
            newLabels.remove(label)
        } else {
            if(newLabels.size >= 3){
                val firstEle = newLabels.first()
                newLabels.remove(firstEle)
            }
            newLabels.add(label)
        }
        println(newLabels)
        labelList.value = newLabels
    }
}