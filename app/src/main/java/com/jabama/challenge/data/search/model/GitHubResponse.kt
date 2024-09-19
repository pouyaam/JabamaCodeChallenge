package com.jabama.challenge.data.search.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubResponse(
    @SerialName("total_count") val totalCount: Int,
    @SerialName("items") val items: List<Repository>
) {
    @Serializable
    data class Repository(
        @SerialName("id") val id: Long,
        @SerialName("name") val name: String,
        @SerialName("full_name") val fullName: String,
        @SerialName("description") val description: String?,
        @SerialName("owner") val owner: Owner,
        @SerialName("stargazers_count") val stargazersCount: Int,
    ) {
        @Serializable
        data class Owner(
            @SerialName("login") val login: String,
            @SerialName("avatar_url") val avatarUrl: String
        )
    }

}
