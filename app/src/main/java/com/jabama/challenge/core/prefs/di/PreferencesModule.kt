package com.jabama.challenge.core.prefs.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.jabama.challenge.core.prefs.PreferencesDataSource
import com.jabama.challenge.core.prefs.PreferencesDataSourceImpl
import org.koin.dsl.module

val preferencesModule = module {
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }

    single<PreferencesDataSource> {
        PreferencesDataSourceImpl(prefs = get())
    }
}
