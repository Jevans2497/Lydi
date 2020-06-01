package com.example.lydi

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.*
import java.lang.Exception

class InternalStorage() {

    fun writeToMemory(context: Context, scaleSets: ScaleSets) {
        val fileName = "ScaleSets.txt"
        try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                val jsonScaleSets = convertObjectToGSON(scaleSets)
                it.write(jsonScaleSets.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readFromMemory(context: Context): ScaleSets? {
        val fileName = "ScaleSets.txt"
        val file = File(context.filesDir, fileName)
        val contents = file.readText()
        return createObjectFromGSON(contents)
    }

    fun convertObjectToGSON(scaleSets: ScaleSets): String {
        val gson = Gson()
        return gson.toJson(scaleSets)
    }

    fun createObjectFromGSON(json: String): ScaleSets? {
        val gson = Gson()
        return gson.fromJson(json, ScaleSets::class.java)
    }
}