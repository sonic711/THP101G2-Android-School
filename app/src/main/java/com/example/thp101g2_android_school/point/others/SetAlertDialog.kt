package com.example.thp101g2_android_school.point.others

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class SetAlertDialog(val context: Context) {


    fun showAlertDialog(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("增加積分")
        alertDialogBuilder.setMessage("登入成功，積分+1" + "\n" + "2017/08/53" + "\t" + "15:30:23" )
        alertDialogBuilder.setPositiveButton("確定") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}






