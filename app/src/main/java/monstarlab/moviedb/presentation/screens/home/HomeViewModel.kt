package monstarlab.moviedb.presentation.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.*
import monstarlab.moviedb.domain.network.ApiModule
import monstarlab.moviedb.domain.network.pojos.mapMovie
import monstarlab.moviedb.domain.usecases.DiscoverMoviesUseCase
import monstarlab.moviedb.domain.usecases.SearchMoviesUseCase
import monstarlab.moviedb.model.Movie


val HomeViewModelAmbient = staticAmbientOf { HomeViewModel() }


class HomeViewModel {
    private val scope =  CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var searchJob: Job? = null
    private val searchMoviesUseCase = SearchMoviesUseCase()
    private val discoverMoviesUseCase = DiscoverMoviesUseCase()
    val searchText = mutableStateOf(TextFieldValue(""))

    val isLoading = mutableStateOf(false)
    val movies = mutableStateOf(emptyList<Movie>())

    init {
        scope.launch { movies.value = discoverMoviesUseCase.discoverMovies() }
    }

    fun clearedSearch() {
        scope.launch {
            isLoading.value = true
            delay(2000)
            movies.value = discoverMoviesUseCase.discoverMovies()
            isLoading.value = false
        }
    }

    fun updateSearchText(textFieldValue: TextFieldValue) {
        searchText.value = textFieldValue
        if(textFieldValue.text.length > 2) {
            searchJob?.cancel()
            searchJob = scope.launch {
                isLoading.value = true
                delay(2000)
                movies.value = searchMoviesUseCase.search(textFieldValue.text)
                isLoading.value = false
            }
        }
    }
}