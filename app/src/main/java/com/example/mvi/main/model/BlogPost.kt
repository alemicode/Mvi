package com.example.mvi.main.model

data class BlogPost(
    val pk: Int? = null,
    val title: String? = null,
    val image: String? = null,
    val body: String? = null
) {

    override fun toString(): String {
        return super.toString()
    }
}