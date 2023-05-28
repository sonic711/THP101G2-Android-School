package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.course.model.Course
import com.example.thp101g2_android_school.course.model.Courses
import com.google.gson.reflect.TypeToken

class CouMainViewModel : ViewModel() {
    private var courseList = mutableListOf<Courses>()
    val courses: MutableLiveData<List<Courses>> by lazy { MutableLiveData<List<Courses>>() }

    init {
        loadData()
    }


     fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            courses.value = courseList
        } else {
            val searchCourseList = mutableListOf<Courses>()
            courseList.forEach { courses ->
                if (courses.courseName?.contains(newText, true) == true) {
                    searchCourseList.add(courses)
                }
            }
           courses.value = searchCourseList
        }
    }
    private fun loadData() {

        val url = "$url/course/"
        val type = object : TypeToken<List<Courses>>() {}.type
        val list = requestTask<List<Courses>>(url, respBodyType = type)
        for (courses in list!!) {
            courseList.add(courses)
        }
        this.courseList = courseList
        this.courses.value = this.courseList
    }

//    private fun loadCourses() {
//        val courseList = mutableListOf<Course>()
//        courseList.add(Course(R.drawable.java, "Java","作者:Ron", "評分:5分(600個評論)","Java 是一種廣泛使用的高級程式語言，具有跨平台特性，由Sun Microsystems（後來被Oracle收購）於1995年推出。它設計的目標是讓開發人員能夠編寫一次並在任何支援Java的平台上運行，這種特性稱為「Write Once, Run Anywhere」（WORA）。"))
//        courseList.add(Course(R.drawable.kotlin, "Kotlin","作者:Ron", "評分:5分(450個評論)","\n" +
//                "Kotlin 是一種現代化的靜態類型程式語言，它運行在Java虛擬機（JVM）上，同時也可以編譯成JavaScript，讓它能在瀏覽器環境中運行。Kotlin由JetBrains開發，於2011年首次發布，並於2016年成為Android官方支援的程式語言。"))
//        courseList.add(Course(R.drawable.com_c, "C","作者:William", "評分:4分(300個評論)","\n" +
//                "C語言是一種通用的高級程式設計語言，由Dennis Ritchie於1972年在貝爾實驗室創建。它是一種結構化的、靜態類型的程式設計語言，廣泛用於系統軟體開發、嵌入式系統和應用程式開發。"))
//        courseList.add(Course(R.drawable.com_csharp, "C#","作者:William", "評分:4分(350個評論)","C#（C Sharp）是一種通用的、面向物件的程式設計語言，由Microsoft開發並在2000年發布。C#主要用於開發.NET平台上的應用程式，並且與其他Microsoft技術緊密集成，如ASP.NET、Windows Forms、WPF、Azure等。"))
//        courseList.add(Course(R.drawable.com_cplusplus, "C++","作者:Sean", "評分:3分(30個評論)","C++是一種通用的、靜態類型的程式設計語言，擁有高度的表達能力和靈活性。它是從C語言發展而來的，加入了許多新的特性和功能，並支援物件導向程式設計（OOP）。"))
//        courseList.add(Course(R.drawable.com_css, "Css","作者:Sean", "評分:3分(20個評論)","CSS（Cascading Style Sheets，層疊樣式表）是一種用於描述網頁文件（如HTML文件）外觀和版面排列的樣式語言。它定義了如何呈現和樣式化網頁內容，包括字型、顏色、間距、邊框、背景等。"))
//        courseList.add(Course(R.drawable.com_html, "HTML","作者:Hank", "評分:2分(3個評論)","HTML（HyperText Markup Language，超文本標記語言）是一種用於創建網頁的標記語言。它使用標記（tags）來描述網頁內容的結構和語義，並且可以包含文字、圖像、視頻、連結和其他多媒體元素。"))
//        courseList.add(Course(R.drawable.com_javascript, "JavaScript","作者:Tony", "評分:4分(600個評論)","JavaScript是一種高級的、動態的程式設計語言，常用於網頁開發，用於增強網頁的互動性和功能性。它是一種解釋性語言，能夠直接在網頁瀏覽器中執行。"))
//        courseList.add(Course(R.drawable.com_kotlin, "Kotlin","作者:Angus", "評分:3分(40個評論)","Kotlin 是一種現代化的靜態類型程式語言，它運行在Java虛擬機（JVM）上，同時也可以編譯成JavaScript，讓它能在瀏覽器環境中運行。Kotlin由JetBrains開發，於2011年首次發布，並於2016年成為Android官方支援的程式語言。"))
//        courseList.add(Course(R.drawable.com_java, "Java","作者:Chuck", "評分:3分(20個評論)","Java 是一種廣泛使用的高級程式語言，具有跨平台特性，由Sun Microsystems（後來被Oracle收購）於1995年推出。它設計的目標是讓開發人員能夠編寫一次並在任何支援Java的平台上運行，這種特性稱為「Write Once, Run Anywhere」（WORA）。"))
//        courseList.add(Course(R.drawable.com_php, "PHP","作者:Amelia", "評分:4分(100個評論)","PHP（Hypertext Preprocessor）是一種通用的開源腳本語言，專為網頁開發而設計。它可以嵌入到HTML中，用於生成動態的網頁內容，處理表單數據，訪問資料庫以及實現各種網站功能。"))
//        courseList.add(Course(R.drawable.com_python, "Python","作者:Rock", "評分:2分(15個評論)","Python 是一種高級、通用、直譯的程式語言，由Guido van Rossum於1991年創建。Python的設計強調代碼的可讀性和簡潔性，並且提供了許多內置的庫和工具，使得開發人員能夠更快速地開發應用程式。"))
//        courseList.add(Course(R.drawable.com_swift, "Swift","作者:John", "評分:3分(60個評論)","Swift是由蘋果（Apple）於2014年推出的一種現代、強大的程式設計語言，專為iOS、macOS、watchOS和tvOS應用程式開發而設計。它結合了安全性、效能和易用性，提供了豐富的功能和工具，使開發者能夠快速創建高品質的應用程式。"))
//        this.courseList = courseList
//        this.courses.value = this.courseList
//    }
}