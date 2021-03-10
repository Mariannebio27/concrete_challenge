package com.mariannecunha.concretechallenge.model

import com.google.gson.annotations.SerializedName

data class User (
    val login: String,
    @SerializedName("avatar_url") val imageUrl: String
)