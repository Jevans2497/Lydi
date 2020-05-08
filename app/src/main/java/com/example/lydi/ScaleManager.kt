package com.example.lydi

class ScaleManager {

    val notes = Notes().notes

    fun selectedScales(): MutableList<String> {
        return major(notes)
    }

    fun major(notes: MutableList<String>): MutableList<String> {
        return notes.map { "$it Major" } as MutableList<String>
    }


}