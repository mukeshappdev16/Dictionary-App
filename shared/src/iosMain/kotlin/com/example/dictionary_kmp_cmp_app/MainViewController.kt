package com.example.dictionary_kmp_cmp_app

import androidx.compose.ui.window.ComposeUIViewController
import com.example.dictionary_kmp_cmp_app.data.ktor.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }