package com.jabama.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jabama.designsystem.component.preview.ThemePreviews
import com.jabama.designsystem.theme.JabamaTheme
import com.jabama.feature.search.R

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onClearQuery: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = query,
                onValueChange = onQueryChanged,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = if (query.isEmpty()) Color.Gray else MaterialTheme.colorScheme.primary
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) { innerTextField ->
                if (query.isEmpty()) {
                    Text(
                        text = stringResource(R.string.search_hint),
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                } else {
                    innerTextField()
                }
            }


            if (query.isNotEmpty()) {
                IconButton(onClick = onClearQuery) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear Icon",
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    JabamaTheme {
        SearchBar(
            query = "",
            onQueryChanged = {},
            onClearQuery = {}
        )
    }
}
