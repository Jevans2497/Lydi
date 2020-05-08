package com.example.lydi

class ScaleManager {

    var notes = mutableListOf("A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab")
    var scales = mutableListOf("Major", "minor", "Dorian", "Phrygian", "Lydian", "Mixolydian", "Aeolian", "Locrian")

    //MARK: Enharmonics
    fun addEnharmonics() {
        notes.addAll(mutableListOf("A#", "Db", "D#", "Gb", "G#"))
    }

    fun removeEnharmonics() {
        notes.removeAll(mutableListOf("A#", "Db", "D#", "Gb", "G#"))
    }

    //MARK: Scales
    fun selectedScales(): MutableList<String> {
        var selectedScales = mutableListOf<String>()
        for (scale in scales) {
            val scaleToAdd = makeScale(scale)
            selectedScales.addAll(scaleToAdd)
        }
        return selectedScales
    }

    fun makeScale(name: String): MutableList<String> {
        return notes.map { "$it $name" } as MutableList<String>
    }
}