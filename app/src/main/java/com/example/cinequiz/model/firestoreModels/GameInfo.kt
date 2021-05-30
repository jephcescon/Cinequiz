package com.example.cinequiz.model.firestoreModels

class GameInfo() {
    var lastGame = LastGame()

    constructor(lastGame :LastGame):this(){
        this.lastGame = lastGame
    }
}