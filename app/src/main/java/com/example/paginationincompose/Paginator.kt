package com.example.paginationincompose

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}