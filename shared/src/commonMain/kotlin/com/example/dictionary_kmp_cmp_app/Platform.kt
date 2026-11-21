package com.example.dictionary_kmp_cmp_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform