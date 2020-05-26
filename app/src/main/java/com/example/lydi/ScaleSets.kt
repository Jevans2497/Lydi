package com.example.lydi


//Data class for storing and retrieving scale sets using Gson
class ScaleSets {

    var sets = mutableListOf<ScaleSet>()

    fun init(sets: MutableList<ScaleSet>) {
        this.sets = sets
    }

    fun ScaleSets() {}
}

class ScaleSet {

    lateinit var name: String
    var enharmonicsEnabled: Boolean = true
    var timerSeconds = -1
    var selectedScales = mutableListOf<String>()

    constructor(name: String, enharmonicsEnabled: Boolean, timerSeconds: Int, selectedScales: MutableList<String>) {
        this.name = name
        this.enharmonicsEnabled = enharmonicsEnabled
        this.timerSeconds = timerSeconds
        this.selectedScales = selectedScales
    }
}