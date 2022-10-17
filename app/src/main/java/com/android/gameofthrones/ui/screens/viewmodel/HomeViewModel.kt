package com.android.gameofthrones.ui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.gameofthrones.domain.mapper.CharacterMapper
import com.android.gameofthrones.domain.model.CharacterDomain
import com.android.gameofthrones.domain.usecase.GetAllCharactersFromApiUseCase
import com.android.gameofthrones.domain.usecase.GetCharactersDataFromDbUseCase
import com.android.gameofthrones.domain.usecase.InsertCharactersDataToDbUseCase
import com.android.gameofthrones.domain.usecase.SearchUseCase
import com.android.gameofthrones.ui.model.Character
import com.android.gameofthrones.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterMapper: CharacterMapper,
    private val getAllCharactersFromApiUseCase: GetAllCharactersFromApiUseCase,
    private val insertCharactersDataToDbUseCase: InsertCharactersDataToDbUseCase,
    private val getCharactersDataFromDbUseCase: GetCharactersDataFromDbUseCase,
    private val searchUseCase: SearchUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Idle)
    val viewState = _viewState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        getCharacterFromDb(throwable.message)
    }

    init {
        getAllDataFromApi()
    }

    fun getAllDataFromApi() {
        _viewState.value = ViewState.Loading
        viewModelScope.launch(dispatchers.io + coroutineExceptionHandler) {
            val data = getAllCharactersFromApiUseCase.execute()
            insertCharactersDataToDbUseCase.execute(data)
            getCharacterFromDb()
        }
    }

    fun search(search: String) {
        viewModelScope.launch {
            searchUseCase
                .execute(search)
                .collect {
                    showData(it, null)
                }
        }
    }

    private fun getCharacterFromDb(errorMessage: String? = null) {
        viewModelScope.launch(dispatchers.main) {
            getCharactersDataFromDbUseCase
                .execute()
                .collect {
                    showData(it, errorMessage)
                }
        }
    }

    private fun showData(weathers: List<CharacterDomain>, errorMessage: String?) {
        val data = weathers.map { domain ->
            characterMapper.mapToPresentation(domain)
        }
        if (data.isNotEmpty()) {
            _viewState.value = ViewState.Success(data, errorMessage)
        } else {
            _viewState.value = ViewState.Error("No Character Found")
        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Success(val data: List<Character>, val errorMessage: String?) : ViewState()
        data class Error(val message: String) : ViewState()
        object Idle : ViewState()
    }
}
