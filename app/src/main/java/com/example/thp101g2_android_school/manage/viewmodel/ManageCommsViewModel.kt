package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.manage.model.Comms
import com.example.thp101g2_android_school.manage.model.CourseReportBean
import com.example.thp101g2_android_school.manage.model.ManageComReportBean
import com.google.gson.reflect.TypeToken

/**
 * 好友列表資料處理
 */
class ManageCommsViewModel : ViewModel() {
    // 原始課程列表
    private var commList = mutableListOf<ManageComReportBean>()

    // 受監控的LiveData，一旦指派新值就會更新課程列表畫面
    val comm: MutableLiveData<List<ManageComReportBean>> by lazy { MutableLiveData<List<ManageComReportBean>>() }

    init {
        loadComments()
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始課程列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            comm.value = commList
        } else {
            val searchCommList = mutableListOf<ManageComReportBean>()
            commList.forEach { comms ->
                if (comms.comPostId.toString() == newText) {
                    searchCommList.add(comms)
                }
            }

            comm.value = searchCommList
        }
    }

    private fun loadComments() {

        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/managecomreport/"
        val type = object : TypeToken<List<ManageComReportBean>>() {}.type
        val list = requestTask<List<ManageComReportBean>>(url, respBodyType = type)
            commList.addAll(list ?: emptyList())
        this.commList = commList
        comm.value = commList
    }
}

        /** 模擬取得遠端資料 */
//    private fun loadComments() {
//        val commList = mutableListOf<ManageComReportBean>()
//        commList.add(ManageComReportBean(1, 1,1,1,"說到底你就是個?",null,null,true,11,22,"11",null,true,"","",null,true,true,))
//        commList.add(ManageComReportBean(2, 2,2,2,"你真的不覺得??",null,null,true,11,22,"11",null,true,"","",null,true,true,))
//        commList.add(ManageComReportBean(3, 3,3,3,"對方說A他就說B抄襲阿",null,null,true,11,22,"11",null,true,"","",null,false,true,))
//
//        this.commList = commList
//        this.comm.value = this.commList
//    }
//}
