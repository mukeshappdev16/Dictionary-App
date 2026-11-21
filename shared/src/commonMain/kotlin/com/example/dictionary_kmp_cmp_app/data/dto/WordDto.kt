package com.example.dictionary_kmp_cmp_app.data.dto


import com.example.dictionary_kmp_cmp_app.domain.models.Word
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordDto(
    @SerialName("meanings")
    val meanings: List<MeaningDto> = listOf(),
    @SerialName("word")
    val word: String = ""
)

fun WordDto.toWord(): Word {
    return Word(
        word = word,
        meanings = meanings.map {
            it.toMeaning()
        }
    )
}