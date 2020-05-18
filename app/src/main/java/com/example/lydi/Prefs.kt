package com.example.lydi

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_FILENAME = "com.Lydi.scalesets"
    val BACKGROUND_COLOR = "background_color"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    fun storeSet(setName: String, setData: String) {
        val editor = prefs.edit()
        editor?.putString(setName, setData)
        editor?.apply()
    }
}