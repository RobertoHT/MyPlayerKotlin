package com.devexperto.kda.myplayerkotlin

sealed class Filter {
    object None : Filter()
    class ByType(val type: Type) : Filter()
}
