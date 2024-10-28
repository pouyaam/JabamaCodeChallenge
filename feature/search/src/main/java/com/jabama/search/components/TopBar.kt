package com.jabama.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jabama.designsystem.component.preview.ThemePreviews
import com.jabama.designsystem.theme.JabamaTheme
import com.jabama.feature.search.R

@Composable
internal fun TopBar(
    onButtonClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(top = 40.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.top_bar),
            modifier = Modifier.padding(start = 8.dp)
        )

        Icon(
            imageVector = Icons.AutoMirrored.TwoTone.ExitToApp,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(24.dp)
                .clickable(onClick = onButtonClick)
        )
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    JabamaTheme {
        TopBar { }
    }
}