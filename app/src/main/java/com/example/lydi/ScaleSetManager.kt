package com.example.lydi

class ScaleSetManager {

    var notes = mutableListOf("A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab")
    var allScales = mutableListOf("Major", "minor", "Dorian", "Phrygian", "Lydian", "Mixolydian", "Aeolian", "Locrian", "Diminished H/W", "Diminished W/H", "Major Pentatonic", "minor Pentatonic", "Augmented", "Whole Tone", "Chromatic")

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
        for (scale in allScales) {
            val scalesToAdd = makeScale(scale)
            selectedScales.addAll(scalesToAdd)
        }
        return selectedScales
    }

    fun makeScale(name: String): MutableList<String> {
        return notes.map { "$it $name" } as MutableList<String>
    }
}