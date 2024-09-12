package com.jabama.challenge.common.utils

import android.graphics.Color


object HexStringToColor {
    fun getIntColor(colorString: String): Int {
        return Color.parseColor(colorString)
    }
}