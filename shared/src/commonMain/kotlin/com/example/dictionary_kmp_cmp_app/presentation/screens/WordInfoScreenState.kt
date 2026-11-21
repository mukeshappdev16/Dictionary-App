package com.example.dictionary_kmp_cmp_app.presentation.screens

import com.example.dictionary_kmp_cmp_app.domain.models.Word

data class WordInfoScreenState(
    val isLoading: Boolean = false,
    val wordInfoList: List<Word> = emptyList(),
    val error: String = ""
)
