package com.example.lydi

var allScales = mutableListOf("Major", "minor", "Dorian", "Phrygian", "Lydian", "Mixolydian", "Aeolian", "Locrian", "Diminished H/W", "Diminished W/H", "Major Pentatonic", "minor Pentatonic", "Augmented", "Whole Tone", "Chromatic")

//Data class for storing and retrieving scale sets using Gson
class ScaleSets {

    var sets = mutableListOf<ScaleSet>()

    fun init(sets: MutableList<ScaleSet>) {
        this.sets = sets
    }

    fun ScaleSets() {}
}

class ScaleSet {

    var notes = mutableListOf("A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab")
    lateinit var name: String
    var enharmonicsEnabled: Boolean = true
    var timerSeconds = 7
    var selectedScales = mutableListOf<String>()

    constructor(name: String, enharmonicsEnabled: Boolean, timerSeconds: Int, selectedScales: MutableList<String>) {
        this.name = name
        this.enharmonicsEnabled = enharmonicsEnabled

        if (enharmonicsEnabled) { addEnharmonics() } else removeEnharmonics()
        this.timerSeconds = timerSeconds
        this.selectedScales = makeScales(selectedScales)
    }

    //MARK: Enharmonics
    fun addEnharmonics() {
        notes.addAll(mutableListOf("A#", "Db", "D#", "Gb", "G#"))
    }

    fun removeEnharmonics() {
        notes.removeAll(mutableListOf("A#", "Db", "D#", "Gb", "G#"))
    }

    //MARK: Scales
    fun makeScales(selectedScales: MutableList<String>): MutableList<String> {
        var scalesWithNotes = mutableListOf<String>()
        for (scale in selectedScales) {
            val scalesToAdd = addNotes(scale)
            scalesWithNotes.addAll(scalesToAdd)
        }
        return scalesWithNotes
    }

    fun addNotes(name: String): MutableList<String> {
        return notes.map { "$it $name" } as MutableList<String>
    }
}