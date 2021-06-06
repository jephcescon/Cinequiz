package com.example.cinequiz.model.firestoreModels

class FireBaseData() {
    var lastGame = LastGame()

    constructor(lastGame: LastGame):this(){
        this.lastGame = lastGame
    }
}