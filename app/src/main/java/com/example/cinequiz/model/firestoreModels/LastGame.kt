package com.example.cinequiz.model.firestoreModels

class LastGame() {
    var lastGame = mutableListOf<Int>()

    constructor(lastGame :MutableList<Int>):this(){
        this.lastGame = lastGame
    }
}