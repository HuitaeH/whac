package com.example.myapplication.model

data class Friend(
    val id: Int,
    val name: String,
    val status: String,
    val profileImage: Int? = null
)