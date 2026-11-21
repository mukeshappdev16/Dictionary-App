package com.example.dictionary_kmp_cmp_app.data.dto


import com.example.dictionary_kmp_cmp_app.domain.models.Definition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefinitionDto(
    @SerialName("definition")
    val definition: String = ""
)

fun DefinitionDto.toDefinition(): Definition {
    return Definition(
        definition = definition
    )
}