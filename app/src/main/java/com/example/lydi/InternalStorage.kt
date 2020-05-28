package com.example.lydi

import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

class InternalStorage: AppCompatActivity() {

    var file = "scalesets"
    val gson = Gson()

    //MARK: Saving data
    fun writeToMemory(scaleSets: ScaleSets) {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(file, 0)
            val scaleSetsAsGson = convertObjectToGSON(scaleSets)
            fileOutputStream.write(scaleSetsAsGson.toByteArray())
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readFromMemory(): ScaleSets? {
        val stringBuilder = StringBuilder()
        try {
            var fileInputStream = openFileInput(file)
            var inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var text = bufferedReader.readLine()
            while (text != null) {
                stringBuilder.append(text)
                text = bufferedReader.readLine()
            }
            inputStreamReader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val jsonString = stringBuilder.toString()
        return createObjectFromGSON(jsonString)
    }

    fun convertObjectToGSON(scaleSets: ScaleSets): String {
        return gson.toJson(scaleSets)
    }

    fun createObjectFromGSON(json: String): ScaleSets? {
        return gson.fromJson(json, ScaleSets::class.java)
    }
}

////https://medium.com/@smjaejin/easily-reading-and-writing-files-to-internal-storage-with-gson-bdba821ca7de
