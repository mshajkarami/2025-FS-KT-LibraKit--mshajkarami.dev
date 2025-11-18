package dev.mshajkarami.fs.kt.bookstack.domin.repository

import dev.mshajkarami.fs.kt.bookstack.common.ResultState
import dev.mshajkarami.fs.kt.bookstack.common.model.BookCategoryModel
import dev.mshajkarami.fs.kt.bookstack.common.model.BookModel
import kotlinx.coroutines.flow.Flow

interface AllBookRepository {
    fun getAllBooks() : Flow<ResultState<List<BookModel>>>
    fun getAllCategory() : Flow<ResultState<List<BookCategoryModel>>>
    fun getAllBooksByCategory(category: String) : Flow<ResultState<List<BookModel>>>
}