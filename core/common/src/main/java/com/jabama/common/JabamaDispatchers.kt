package com.jabama.common

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

enum class JabamaDispatchers {
    IO,
    DEFAULT
}

class DispatcherQualifier(dispatcher: JabamaDispatchers) : Qualifier {
    override val value: QualifierValue = dispatcher.name
}