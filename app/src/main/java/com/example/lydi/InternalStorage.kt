package com.example.lydi

import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

class InternalStorage: AppCompatActivity() {

    private val file = "scaleSets.txt"
    val gson = Gson()

    //MARK: Saving data
    fun writeToMemory(data: String) {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(file, 0)
            fileOutputStream.write("".toString().toByteArray())
            readFromMemory()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readFromMemory() {
        try {
            var fileInputStream = openFileInput(file)
            var inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var text = bufferedReader.readLine()
            while (text != null) {
                stringBuilder.append(text)
                text = bufferedReader.readLine()
            }
//            saveButton.text = stringBuilder
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun convertObjectToGSON(myObject: Any): String {
        return gson.toJson(myObject)
    }

    fun createObjectFromGSON(json: String): Any {
//https://medium.com/@smjaejin/easily-reading-and-writing-files-to-internal-storage-with-gson-bdba821ca7de
    }
}