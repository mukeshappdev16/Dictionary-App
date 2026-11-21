package com.example.dictionary_kmp_cmp_app.domain.models


data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition> = listOf()
)