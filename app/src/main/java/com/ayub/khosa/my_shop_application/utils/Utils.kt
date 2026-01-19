package com.ayub.khosa.my_shop_application.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.ayub.khosa.my_shop_application.utils.Constants.Companion.PREF_FIREBASE_USERID_KEY

object Utils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager
            .getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun   getUserIdFromSharedPref(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString(PREF_FIREBASE_USERID_KEY, "0").toString()
    }

}

