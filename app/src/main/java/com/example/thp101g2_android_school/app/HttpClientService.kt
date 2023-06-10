package com.example.thp101g2_android_school.app

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.thp101g2_android_school.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlinx.coroutines.*
import java.lang.reflect.Type
import java.net.CookieHandler
import java.net.CookieManager
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.URL
import java.sql.Date
import java.sql.Time
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val url: String = "http://10.0.2.2:8080/THP101G2-WebServer-School/"

/**
 *  資料庫抓圖片出來用：
 *  後端Java型別為byte[]，Kotlin用ByteArray接受後
 *  用此方法將 ByteArray 轉成Bitmap 就可以imageView.setImageBitmap(放進來)
 *  @param ByteArray
 *  @return Bitmap物件
 *  @author Sean
 * */
fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}


/** 社群用
 *  用字串尋找對應的圖片檔
 *  @param str 資源的字串名稱
 *  @return R.drawable的 id
 *  @author Sean
 */
inline fun getStringResourceId(str: String): Int {
    return when (str) {
        "程式語言" -> R.drawable.com_android
        "Java" -> R.drawable.com_java
        "Kotlin" -> R.drawable.com_kotlin
        "Swift" -> R.drawable.com_swift
        "音樂" -> R.drawable.com_music_note
        "鋼琴" -> R.drawable.com_piano
        "爵士鼓" -> R.drawable.com_drum
        "烹飪" -> R.drawable.com_cooking
        "中餐" -> R.drawable.com_buns
        "西餐" -> R.drawable.com_dish
        "語言" -> R.drawable.world
        "中文" -> R.drawable.com_language
        "西班牙文" -> R.drawable.com_hola
        "英文" -> R.drawable.com_eng
        else -> R.drawable.com_mary
    }
}

inline fun <reified T> requestTask(
    url: String,
    method: String = "GET",
    reqBody: Any? = null,
    respBodyType: Type = T::class.java,
): T? = runBlocking {
    val deferred: Deferred<T?> = coroutineScope {
        async(Dispatchers.IO) {
            if (CookieHandler.getDefault() == null) {
                CookieHandler.setDefault(CookieManager())
            }
            request<T>(url, method, reqBody, respBodyType)
        }
    }
    deferred.await()
}

inline fun <reified T> request(
    url: String,
    method: String = "GET",
    reqBody: Any? = null,
    respBodyType: Type = T::class.java,
): T? {
    var conn: HttpURLConnection? = null
    var result: T? = null
    try {
        conn = URL(url).openConnection() as HttpURLConnection
        with(conn) {
            requestMethod = method
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            useCaches = false
            doOutput = reqBody != null
            reqBody?.run {
                outputStream.use {
                    val writer = outputStream.writer()
                    writer.write(GSON.toJson(reqBody))
                    writer.flush()
                }
            }
            if (responseCode == HTTP_OK) {
                inputStream.use {
                    result = GSON.fromJson<T>(inputStream.reader(), respBodyType)
                }
            }
        }
    } finally {
        conn?.disconnect()
    }
    return result
}

val GSON: Gson = GsonBuilder()
    .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
    .registerTypeAdapter(Time::class.java, CustomTimeTypeAdapter())
    .registerTypeAdapter(Timestamp::class.java, CustomTimestampTypeAdapter())
    .create()

class CustomDateTypeAdapter : TypeAdapter<Date>() {
    private val dateFormat = SimpleDateFormat("M月 d, yyyy", Locale.CHINESE)

    override fun write(out: JsonWriter, value: Date?) {
        out.value(value?.let { dateFormat.format(it) })
    }

    override fun read(`in`: JsonReader): Date? {
        val dateString = `in`.nextString()
        return try {
            val parsedDate = dateFormat.parse(dateString)
            Date(parsedDate.time)
        } catch (e: ParseException) {
            null
        }
    }
}

class CustomTimeTypeAdapter : TypeAdapter<Time>() {
    private val timeFormat = SimpleDateFormat("hh:mm:ss a", Locale.CHINESE)

    override fun write(out: JsonWriter, value: Time?) {
        out.value(value?.let { timeFormat.format(it) })
    }

    override fun read(`in`: JsonReader): Time? {
        val timeString = `in`.nextString()
        return try {
            val parsedDate = timeFormat.parse(timeString)
            Time(parsedDate.time)
        } catch (e: ParseException) {
            null
        }
    }
}

class CustomTimestampTypeAdapter : TypeAdapter<Timestamp>() {
    private val timestampFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())

    override fun write(out: JsonWriter, value: Timestamp?) {
        out.value(value?.let { timestampFormat.format(it) })
    }

    override fun read(`in`: JsonReader): Timestamp? {
        val timestampString = `in`.nextString()
        return try {
            val parsedDate = timestampFormat.parse(timestampString)
            Timestamp(parsedDate.time)
        } catch (e: ParseException) {
            null
        }
    }
}