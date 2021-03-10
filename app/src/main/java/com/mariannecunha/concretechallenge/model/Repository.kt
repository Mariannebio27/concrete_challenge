package com.mariannecunha.concretechallenge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository (
    val name: String,
    val description: String,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("forks_count")  val forksCount: Int,
    val owner: Owner
) : Parcelable