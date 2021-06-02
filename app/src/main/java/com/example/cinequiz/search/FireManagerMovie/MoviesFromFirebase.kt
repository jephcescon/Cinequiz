package com.example.cinequiz.search.FireManagerMovie

class MoviesFromFirebase() {
 var buscasRecentes = arrayListOf<BuscasRecentes>()

    constructor(buscasRecentes : ArrayList<BuscasRecentes>) : this(){
        this.buscasRecentes = buscasRecentes

    }
}