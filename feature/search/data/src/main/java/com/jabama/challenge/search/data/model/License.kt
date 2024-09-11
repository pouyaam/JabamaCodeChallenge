package com.jabama.challenge.search.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class License(
    val key: String?,
    val name: String?,
    @SerializedName("spdx_id")
    val spdxId: String?,
    val url: String?,
    @SerializedName("node_id")
    val nodeId: String?
)