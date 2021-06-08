package com.example.cinequiz.search.fireManagerMovie

class BuscasRecentes() {
    var id : Int? = 1
    var title:String?  = ""
    var cover:String?  = ""
    var poster:String?  = ""
    var type:Boolean?  = true

    constructor(id: Int?, title: String?, cover: String?, poster: String?, type: Boolean?) : this(){
        this.id = id
        this.title = title
        this.cover = cover
        this.poster = poster
        this.type = type
    }
}