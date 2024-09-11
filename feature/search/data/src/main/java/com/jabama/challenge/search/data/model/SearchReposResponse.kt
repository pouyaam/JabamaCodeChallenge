package com.jabama.challenge.search.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SearchReposResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<Item>
)