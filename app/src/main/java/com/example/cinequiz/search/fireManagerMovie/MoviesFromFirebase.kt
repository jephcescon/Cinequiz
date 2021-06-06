package com.example.cinequiz.search.fireManagerMovie

class MoviesFromFirebase() {
 var buscasRecentes = arrayListOf<BuscasRecentes>()

    constructor(buscasRecentes : ArrayList<BuscasRecentes>) : this(){
        this.buscasRecentes = buscasRecentes

    }
}