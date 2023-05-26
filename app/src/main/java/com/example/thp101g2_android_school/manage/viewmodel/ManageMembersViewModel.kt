package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.manage.model.Members

class ManageMembersViewModel : ViewModel() {
    private var memberList = mutableListOf<Members>()
    val memberso: MutableLiveData<List<Members>> by lazy { MutableLiveData<List<Members>>() }

    init {
        loadMembers()
    }

    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            memberso.value = memberList
        } else {
            val searchMemberList = mutableListOf<Members>()
            memberList.forEach { members ->
                if (members.memberID.contains(newText, true)) {
                    searchMemberList.add(members)
                }
            }
            memberso.value = searchMemberList
        }
    }

    private fun loadMembers() {
        val memberList = mutableListOf<Members>()
        memberList.add(Members("123", "1", "0939847591"))
        memberList.add(Members("1233", "2", "0988888111"))
        memberList.add(Members("345", "3", "0966789123"))

        this.memberList = memberList
        this.memberso.value = this.memberList
    }
}