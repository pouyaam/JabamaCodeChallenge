package com.jabama.common.di

import com.jabama.common.DispatcherQualifier
import com.jabama.common.JabamaDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatchersModule = module {
    single(qualifier = DispatcherQualifier(JabamaDispatchers.IO)) { Dispatchers.IO }
    single(qualifier = DispatcherQualifier(JabamaDispatchers.DEFAULT)) { Dispatchers.Default }
}