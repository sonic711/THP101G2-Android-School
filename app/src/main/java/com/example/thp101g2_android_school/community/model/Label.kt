package com.example.thp101g2_android_school.community.model

import java.io.Serializable

/**
 * 社群文章標籤
 */
data class Label(
    var comLabelId: String? = null,
    var comPostId: String? = null,
    var comLabelName: String? = null,
    var comLabelTime: String? = null
) : Serializable {
}