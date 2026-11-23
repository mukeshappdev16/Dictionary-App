package com.example.dictionary_kmp_cmp_app.data.repository

import com.example.dictionary_kmp_cmp_app.data.database.WordDao
import com.example.dictionary_kmp_cmp_app.data.dto.WordDto
import com.example.dictionary_kmp_cmp_app.data.dto.toWord
import com.example.dictionary_kmp_cmp_app.data.dto.toWordEntity
import com.example.dictionary_kmp_cmp_app.domain.models.Word
import com.example.dictionary_kmp_cmp_app.domain.repository.Dictionary
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class DictionaryRepositoryImpl(private val httpClient: HttpClient, private val wordDao: WordDao) :
    Dictionary {
    private val logger = co.touchlab.kermit.Logger.withTag("DictionaryRepositoryImpl")
    var baseUrl: String = "https://api.dictionaryapi.dev/api/v2/entries/en/"

    override suspend fun getWordInfo(word: String): Result<List<Word>> {
        return try {
            logger.d { "Fetching info for word: $word" }
            val response = httpClient.get(baseUrl + word).body<List<WordDto>>()
            logger.d { "Successfully fetched ${response.size} entries for word: $word" }

            Result.success(response.map {
                wordDao.addWord(it.toWordEntity())
                it.toWord()
            })

        } catch (exc: Exception) {
            logger.e(exc) { "Error fetching info for word: $word" }
            Result.failure(exc)
        }
    }
}