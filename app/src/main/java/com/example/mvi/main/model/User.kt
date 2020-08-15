package com.example.mvi.main.model

data class User(

    val email : String? = null,
    val username : String? = null,
    val image : String? = null
){

    override fun toString(): String {
        return "file (${email}  ${username} ${image})"
    }
}