package com.jabama.challenge.search.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Permissions(
    val admin: Boolean?,
    val maintain: Boolean?,
    val push: Boolean?,
    val triage: Boolean?,
    val pull: Boolean?
)