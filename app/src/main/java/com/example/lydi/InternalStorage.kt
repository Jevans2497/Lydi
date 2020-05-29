package com.example.lydi

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.*
import java.lang.Exception

//class InternalStorage() {
//
//    var context: Context? = null
//    var file: File? = null
//    val gson = Gson()
//
//    constructor(context: Context) : this() {
//        this.context = context
//    }
//}



//    MARK: Saving data
//    fun writeToMemory(scaleSets: ScaleSets) {
//        val fileOutputStream: FileOutputStream
//        try {
//            fileOutputStream = openFileOutput("ScaleSets.txt", 0)
//            FileOutputStream(file).use {
//                val scaleSetsAsGson = convertObjectToGSON(scaleSets)
//                it.write(scaleSetsAsGson.toByteArray())
//            }
////            val scaleSetsAsGson = convertObjectToGSON(scaleSets)
////            fileOutputStream.write(scaleSetsAsGson.toByteArray())
////            fileOutputStream.flush()
////            fileOutputStream.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

    fun writeToMemory(context: Context, scaleSets: ScaleSets) {
        Log.d("GOOPY", context.filesDir.path)
//        try {
//            val fileOut = FileWriter("ScaleSets.txt")
//            fileOut.write("TEST TEXT")
//            fileOut.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }

    fun readFromMemory(): ScaleSets? {
//        val stringBuilder = StringBuilder()
//        try {
//            var fileInputStream = openFileInput(file)
//            var inputStreamReader = InputStreamReader(fileInputStream)
//            val bufferedReader = BufferedReader(inputStreamReader)
//            var text = bufferedReader.readLine()
//            while (text != null) {
//                stringBuilder.append(text)
//                text = bufferedReader.readLine()
//            }
//            inputStreamReader.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        val jsonString = stringBuilder.toString()
//        return createObjectFromGSON(jsonString)
        return null
    }

    fun convertObjectToGSON(scaleSets: ScaleSets): String {
        val gson = Gson()
        return gson.toJson(scaleSets)
    }

    fun createObjectFromGSON(json: String): ScaleSets? {
        val gson = Gson()
        return gson.fromJson(json, ScaleSets::class.java)
    }
//}

////https://medium.com/@smjaejin/easily-reading-and-writing-files-to-internal-storage-with-gson-bdba821ca7de
