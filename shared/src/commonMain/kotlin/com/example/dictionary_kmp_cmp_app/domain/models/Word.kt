package com.example.dictionary_kmp_cmp_app.domain.models


data class Word(
    val meanings: List<Meaning> = listOf(),
    val word: String = ""
)