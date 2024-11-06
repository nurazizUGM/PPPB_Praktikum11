package com.example.praktikum11.model

import com.google.gson.annotations.SerializedName

data class Authors(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String
)
