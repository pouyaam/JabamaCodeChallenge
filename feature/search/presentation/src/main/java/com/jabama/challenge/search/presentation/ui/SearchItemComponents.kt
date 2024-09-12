package com.jabama.challenge.search.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.jabama.challenge.search.domain.model.SearchResult
import com.jabama.challenge.search.presentation.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SearchItem(modifier: Modifier = Modifier, item: SearchResult) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 1.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                model = item.ownerAvatarUrl,
                contentDescription = item.ownerName,
                loading = placeholder(resourceId = R.drawable.avatar_place_holder),
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
            )
            Text(text = item.ownerName.orEmpty(), style = MaterialTheme.typography.labelSmall)
        }
        Text(text = item.name.orEmpty(), style = MaterialTheme.typography.titleMedium)
        item.description?.takeIf(String::isNotBlank)?.apply {
            Text(
                text = this,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item.private.takeIf { it }?.apply {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(R.string.private_repo_icon_content_description),
                    tint = MaterialTheme.colorScheme.surfaceTint,
                    modifier = Modifier.size(20.dp)
                )
            }
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = stringResource(R.string.repo_stars_icon_content_description),
                tint = Color.Yellow,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = item.stargazersCount.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Box(
                modifier = modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(Color(item.languageColor))
            )
            Text(
                text = item.language.orEmpty(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewEachItem() {
    SearchItem(
        item = SearchResult(
            id = -1,
            ownerName = "rezazarchi",
            private = true,
            name = "Persian-Calendar-Tile",
            ownerAvatarUrl = "https://avatars.githubusercontent.com/u/8654398?s=48&v=4",
            description = "Simple Persian calendar tile for the WearOS",
            stargazersCount = 5,
            languageColor = 0xFF9CCC65.toInt(),
            language = "Kotlin",
        )
    )
}

@Composable
fun ShimmerSearchItem() {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp)),
        shape = MaterialTheme.shapes.large,
        colors = if (isSystemInDarkTheme().not()) {
            CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        } else {
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        },
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .shimmerEffect(),
            )
            Column(
                modifier = Modifier.weight(1F),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(20.dp)
                        .shimmerEffect(),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(20.dp)
                        .shimmerEffect(),
                )
            }
        }
    }
}