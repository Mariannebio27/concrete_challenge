package com.mariannecunha.concretechallenge.model

import com.google.gson.annotations.SerializedName

data class GlobalRepository (
    @SerializedName("items") val repositories: List<Repository>
)