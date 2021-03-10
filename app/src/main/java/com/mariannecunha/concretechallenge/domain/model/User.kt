package com.mariannecunha.concretechallenge.domain.model

import com.google.gson.annotations.SerializedName

data class User (
    val login: String,
    @SerializedName("avatar_url") val imageUrl: String
)