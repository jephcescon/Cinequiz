package com.example.cinequiz.catalog

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecycleScroll(val onRequestMore: () -> Unit): RecyclerView.OnScrollListener() {
    var requesting = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val target = recyclerView.layoutManager as GridLayoutManager
        val totalItemCount = target.itemCount
        val lastVisible = target.findLastVisibleItemPosition()
        val lastItem = lastVisible + 6 >= totalItemCount
        if (totalItemCount > 0 && lastItem && !requesting){
            onRequestMore()
            setRequestingNextPage(true)
        }
    }

    fun setRequestingNextPage(requesting: Boolean){
        this.requesting = requesting
    }
}