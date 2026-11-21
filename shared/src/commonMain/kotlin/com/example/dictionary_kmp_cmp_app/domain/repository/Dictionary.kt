package com.example.dictionary_kmp_cmp_app.domain.repository

import com.example.dictionary_kmp_cmp_app.domain.models.Word

interface Dictionary {
    suspend fun getWordInfo(word: String):Result<List<Word>>
}