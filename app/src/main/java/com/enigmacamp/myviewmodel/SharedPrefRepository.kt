package com.enigmacamp.myviewmodel

import android.content.Context

class SharedPrefRepository(context: Context) {
    private val pref = context.getSharedPreferences("App_Pref", Context.MODE_PRIVATE)
    private val editor = pref.edit()

    fun put(key: String, value: String) {
        editor.apply {
            putString(key, value)
            apply()
        }
    }

    fun get(key: String) = pref.getString(key, "")
}