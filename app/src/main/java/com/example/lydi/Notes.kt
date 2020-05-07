package com.example.lydi

class Notes {
    val notes = mutableListOf("A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab")

    fun addEnharmonics() {
        notes.addAll(mutableListOf("A#", "Db", "D#", "Gb", "G#"))
    }

    fun removeEnharmonics() {
        notes.removeAll(mutableListOf("A#", "Db", "D#", "Gb", "G#"))
    }
}