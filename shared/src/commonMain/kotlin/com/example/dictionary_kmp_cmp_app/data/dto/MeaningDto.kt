package com.example.dictionary_kmp_cmp_app.data.dto


import com.example.dictionary_kmp_cmp_app.domain.models.Meaning
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeaningDto(
    @SerialName("definitions")
    val definitions: List<DefinitionDto> = listOf(),
    @SerialName("partOfSpeech")
    val partOfSpeech: String = ""
)

fun MeaningDto.toMeaning(): Meaning {
    return Meaning(
        partOfSpeech = partOfSpeech,
        definitions = definitions.map {
            it.toDefinition()
        }
    )
}