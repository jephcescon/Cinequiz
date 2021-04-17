package com.example.cinequiz.search.model

import androidx.lifecycle.MutableLiveData

object ClickSearch {
    var textSearch:String = ""
    val searchTrue by lazy { MutableLiveData<Boolean>() }

    fun setValue() {
        searchTrue.postValue(true)
    }
}