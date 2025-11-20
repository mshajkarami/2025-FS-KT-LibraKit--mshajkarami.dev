package dev.mshajkarami.fs.kt.bookstack.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mshajkarami.fs.kt.bookstack.common.ResultState
import dev.mshajkarami.fs.kt.bookstack.common.model.BookCategoryModel
import dev.mshajkarami.fs.kt.bookstack.common.model.BookModel
import dev.mshajkarami.fs.kt.bookstack.domin.repository.AllBookRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(val repo : AllBookRepository) : ViewModel() {

    private val _state : MutableState<ItemState> = mutableStateOf(ItemState())
    val state : MutableState<ItemState> = _state

    fun bringAllBooks(){
        viewModelScope.launch {
            repo.getAllBooks().collect {
                when(it){
                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _state.value = ItemState(items = it.data)
                    }

                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }
                }
            }
        }
    }
    fun bringCategories(){
        viewModelScope.launch {
            repo.getAllCategory().collect {
                when(it){
                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _state.value = ItemState(category = it.data)
                    }

                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }
                }
            }
        }
    }

    fun bringAllBooksByCategories(category : String){
        viewModelScope.launch {
            repo.getAllBooksByCategory(category).collect {
                when(it){
                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _state.value = ItemState(items = it.data)
                    }

                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }
                }
            }
        }
    }

}

data class ItemState(
    val isLoading: Boolean = false,
    val items: List<BookModel> = emptyList(),
    val error: String = "",
    val category: List<BookCategoryModel> = emptyList(),
)
