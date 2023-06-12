package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.manage.model.Members
import com.example.thp101g2_android_school.manage.model.SelectMemberBean
import com.example.thp101g2_android_school.manage.model.TeaApplyBean
import com.google.gson.reflect.TypeToken

class ManageMembersViewModel : ViewModel() {
    private var memberList = mutableListOf<SelectMemberBean>()


    val memberso: MutableLiveData<List<SelectMemberBean>> by lazy { MutableLiveData<List<SelectMemberBean>>() }

    init {
        loadMembers()
    }


    //按鍵事件點擊
    fun filterMembersByCondition(condition: Boolean) {
        val filteredMembers = memberList.filter { member ->
            member.memberStatus == condition
        }
        memberso.value = filteredMembers
    }

    fun search(newText: String?) {
        if (newText.isNullOrEmpty()) {
            memberso.value = memberList
        } else {
            val filteredMembers = memberList.filter { member ->
                (member.memberNo?.contains(newText, ignoreCase = true)) == true
            }
            memberso.value = filteredMembers
        }
    }


    private fun loadMembers() {
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/teaapply/another"
        val type = object : TypeToken<List<SelectMemberBean>>() {}.type
        val list = requestTask<List<SelectMemberBean>>(url, respBodyType = type)
        memberList.addAll(list ?: emptyList())
        this.memberList = memberList
        memberso.value = memberList

    }


    // 其他方法和代码...
}



//    private fun loadMembers() {
//        val memberList = mutableListOf<TeaApplyBean>()
//        memberList.add(TeaApplyBean("100033", "112212", "123", "111111","知錯能改....",null,null,true))
//        memberList.add(TeaApplyBean("122222", "Java?Js?", "124", "112233", "我感覺你是不是...",null,null,false))
//        memberList.add(TeaApplyBean("112212", "nickben", "125","333222","福佬奶奶過街,我不罵你",null,null,false))
//        memberList.add(TeaApplyBean("100009", "cc309", "126","333333","AtoBtoCtoD",null,null,true))
//        memberList.add(TeaApplyBean("123324", "697071ee", "127","136123","ADCDB五題答案",null,null,true))
//        memberList.add(TeaApplyBean("112212", "nickben", "128","131366","MIT",null,null,true))
//        memberList.add(TeaApplyBean("112212", "nickben", "129","124588","AAAAAA",null,null,true))
//        memberList.add(TeaApplyBean("112212", "nickben", "139","154344","bbbbbb",null,null,false))
//        memberList.add(TeaApplyBean("112212", "nickben", "246","128765","cccccc",null,null,true))
//        memberList.add(TeaApplyBean("112212", "nickben", "369","121488","哩起傚喔......",null,null,true))
//        memberList.add(TeaApplyBean("112212", "nickben", "321","875109","tothesky",null,null,true))


//        memberso.value = memberList
//    }
//}
