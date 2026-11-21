package com.example.dictionary_kmp_cmp_app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.dictionary_kmp_cmp_app.data.ktor.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Dictionary_KMP_CMP_App",
        ) {
            App()
        }
    }
}