package com.mariannecunha.concretechallenge.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class PullRequest (
    @SerializedName("html_url") val url: String,
    val title: String,
    val user: User,
    val body: String,
    @SerializedName("created_at") val date: Date
)