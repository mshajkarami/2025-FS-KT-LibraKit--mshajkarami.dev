package dev.mshajkarami.fs.kt.bookstack.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object HomeScreen

    @Serializable
    object Data class BooksByCategory(val category : String)

    @Serializable
    data class ShowPdfScreen(val url : String)
}