package com.example.cinequiz.model.firestoreModels

import com.example.cinequiz.search.FireManagerMovie.BuscasRecentes

class FireBaseData() {
    var lastGame = LastGame()

    constructor(lastGame: LastGame):this(){
        this.lastGame = lastGame
    }
}