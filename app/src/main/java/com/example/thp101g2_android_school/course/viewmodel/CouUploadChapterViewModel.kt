package com.example.thp101g2_android_school.course.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.course.model.Chapter
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.net.URI

class CouUploadChapterViewModel : ViewModel() {
    val chapterName : MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>()}
    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val no: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val chapter: MutableLiveData<Chapter> by lazy { MutableLiveData<Chapter>() }

}