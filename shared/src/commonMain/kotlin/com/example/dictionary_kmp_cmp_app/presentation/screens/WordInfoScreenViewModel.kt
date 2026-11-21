package com.example.dictionary_kmp_cmp_app.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.example.dictionary_kmp_cmp_app.domain.usecase.GetWordInfoUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class WordInfoScreenViewModel(
    private val getWordInfoUseCase: GetWordInfoUseCase
) : ViewModel() {

    private val logger = Logger.withTag("WordInfoScreenViewModel")

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _state = MutableStateFlow(WordInfoScreenState())
    val state: StateFlow<WordInfoScreenState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(500L)
                .map { it.trim() }
                .distinctUntilChanged()
                .collect { query ->
                    if (query.isBlank()) {
                        _state.update {
                            it.copy(isLoading = false, wordInfoList = emptyList(), error = "")
                        }
                        return@collect
                    }

                    _state.update { it.copy(isLoading = true, error = "") }
                    logger.d { "Searching word: $query" }

                    val result = getWordInfoUseCase(query)

                    result.onSuccess { list ->
                        logger.d { "Received ${list.size} entries for: $query" }
                        _state.update {
                            it.copy(isLoading = false, wordInfoList = list, error = "")
                        }
                    }.onFailure { error ->
                        logger.e(error) { "Error fetching word info" }
                        _state.update {
                            it.copy(
                                isLoading = false,
                                wordInfoList = emptyList(),
                                error = error.message ?: "An unexpected error occurred"
                            )
                        }
                    }
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}
