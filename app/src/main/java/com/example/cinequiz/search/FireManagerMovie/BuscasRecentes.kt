package com.example.cinequiz.search.FireManagerMovie

class BuscasRecentes() {
    var id : Int? = 1
    var title  = ""
    var cover = ""
    var poster = ""

    constructor(id: Int?, title: String, cover: String, poster: String) : this(){
        this.id = id
        this.title = title
        this.cover = cover
        this.poster = poster
    }
}