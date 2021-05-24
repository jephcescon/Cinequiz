package com.example.cinequiz.model.firestoreModels

class LastGame() {
    var lastGame = mutableListOf<Int>()
    var date = mutableListOf<String>()

    constructor(lastGame :MutableList<Int>, date : MutableList<String>):this(){
        this.lastGame = lastGame
        this.date = date
    }
}