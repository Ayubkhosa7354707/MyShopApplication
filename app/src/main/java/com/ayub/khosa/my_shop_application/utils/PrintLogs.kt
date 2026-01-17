package com.ayub.khosa.my_shop_application.utils

import android.util.Log


object PrintLogs {
    private const val logs_enable = true
    private const val TAG = "AYUB"
    fun printInfo(message: String) {
        if (logs_enable) Log.i(TAG, "" + message)
    }


    fun printE(message: String) {
        if (logs_enable) Log.e(TAG, "" + message)
    }

    fun printE(tag: String, message: String) {
        if (logs_enable) Log.e("" + tag, "" + message)
    }

    fun printD(message: String) {
        if (logs_enable) Log.d(TAG, "" + message)
    }

    fun printD(tag: String, message: String) {
        if (logs_enable) Log.d("" + tag, "" + message)
    }
}
