package com.jabama.challenge.core.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import org.koin.dsl.module

val coreModule = module {
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }
}
