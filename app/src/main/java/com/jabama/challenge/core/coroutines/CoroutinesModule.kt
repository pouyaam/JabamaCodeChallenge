package com.jabama.challenge.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

val MainDispatcherNamed = named("MainDispatcher")
val MainScopeNamed = named("MainScope")

val IoDispatcherNamed = named("IoDispatcher")
val IoScopeNamed = named("IoScope")

val DefaultDispatcherNamed = named("DefaultDispatcher")
val DefaultScopeNamed = named("DefaultScope")

val coroutinesModule = module {
    single<CoroutineDispatcher>(MainDispatcherNamed) { Dispatchers.Main }
    single<CoroutineDispatcher>(IoDispatcherNamed) { Dispatchers.IO }
    single<CoroutineDispatcher>(DefaultDispatcherNamed) { Dispatchers.Default }

    single<CoroutineScope>(MainScopeNamed) {
        CoroutineScope(get<CoroutineDispatcher>(MainDispatcherNamed) + SupervisorJob())
    }

    single<CoroutineScope>(IoScopeNamed) {
        CoroutineScope(get<CoroutineDispatcher>(IoDispatcherNamed) + SupervisorJob())
    }

    single<CoroutineScope>(DefaultScopeNamed) {
        CoroutineScope(get<CoroutineDispatcher>(DefaultDispatcherNamed) + SupervisorJob())
    }
}