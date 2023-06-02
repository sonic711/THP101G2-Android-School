package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.manage.model.Members

class ManageMembersViewModel : ViewModel() {
    private var memberList = mutableListOf<Members>()
    val memberso: MutableLiveData<List<Members>> by lazy { MutableLiveData<List<Members>>() }

    init {
        loadMembers()
    }

    fun filterMembersByCondition(condition: Boolean) {
        val filteredMembers = memberList.filter { member ->
            member.state == condition
        }
        memberso.value = filteredMembers
    }

    fun search(newText: String?) {
        if (newText.isNullOrEmpty()) {
            memberso.value = memberList
        } else {
            val filteredMembers = memberList.filter { member ->
                member.memberID.contains(newText, ignoreCase = true)
            }
            memberso.value = filteredMembers
        }
    }

    private fun loadMembers() {
        memberList = mutableListOf(
            Members(R.drawable.baseline_people_24,"101051", "coco397", "0939847591", true),
            Members(R.drawable.baseline_people_24,"112212", "nickben", "0988888111", false),
            Members(R.drawable.baseline_people_24,"100009", "cc309", "0966789123", true),
            Members(R.drawable.baseline_people_24,"123324", "697071ee", "0939847591", true),
            Members(R.drawable.baseline_people_24,"152223", "yeeee", "0988888111", false),
            Members(R.drawable.baseline_people_24,"135785", "alavater", "0966789123", true),
            Members(R.drawable.baseline_people_24,"114158", "cryie", "0966789123", true),
            Members(R.drawable.baseline_people_24,"314687", "gg3:0", "0939847591", true),
            Members(R.drawable.baseline_people_24,"322121", "Nxni", "0966789123", true),
            Members(R.drawable.baseline_people_24,"111111", "NdoubleA", "0939847591", true),
            Members(R.drawable.baseline_people_24,"222222", "ABC", "0966789123", true),
            Members(R.drawable.baseline_people_24,"371234", "NANA", "0939847591", true)
        )
        memberso.value = memberList
    }
}