package com.example.dictionary_kmp_cmp_app.domain.usecase

import com.example.dictionary_kmp_cmp_app.domain.models.Word
import com.example.dictionary_kmp_cmp_app.domain.repository.Dictionary

class GetWordInfoUseCase(private val dictionary: Dictionary) {
    suspend operator fun invoke(word: String): Result<List<Word>> {
        if (word.isBlank()) {
            return Result.success(emptyList())
        }
        return dictionary.getWordInfo(word)
    }
}