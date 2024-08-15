package com.jabama.challenge.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class Shapes(
    val s4: Shape = RoundedCornerShape(4.dp),
    val s8: Shape = RoundedCornerShape(8.dp)
)
