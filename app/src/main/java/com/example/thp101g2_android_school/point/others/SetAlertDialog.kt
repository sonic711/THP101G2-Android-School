package com.example.thp101g2_android_school.point.others

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.point.model.Point
import com.google.gson.reflect.TypeToken


class SetAlertDialog(val context: Context) {


    fun showAlertDialogForSC(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("增加積分")
        alertDialogBuilder.setMessage("課程完成率達100%，積分+20")
        alertDialogBuilder.setPositiveButton("確定") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    fun showAlertDialogForCMT(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("增加積分")
        alertDialogBuilder.setMessage("課程評分完成，積分+5")
        alertDialogBuilder.setPositiveButton("確定") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun showAlertDialogForMLR(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("增加積分")
        alertDialogBuilder.setMessage("登入成功，積分+1")
        alertDialogBuilder.setPositiveButton("確定") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }






}






