package com.mariannecunha.concretechallenge.domain.model

import com.google.gson.annotations.SerializedName

data class GlobalRepository(
    @SerializedName("items") val repositories: List<Repository>
)
