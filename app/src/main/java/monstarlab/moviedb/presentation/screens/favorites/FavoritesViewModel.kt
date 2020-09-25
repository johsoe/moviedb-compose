package monstarlab.moviedb.presentation.screens.favorites

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticAmbientOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import monstarlab.moviedb.model.Movie
import monstarlab.moviedb.presentation.screens.home.HomeViewModel

val FavoritesViewModelAmbient = staticAmbientOf { FavoritesViewModel() }

class FavoritesViewModel {
    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies: LiveData<List<Movie>> = _movies

    fun toggleMovie(movie: Movie) {
        val movies = movies.value ?: return
        if(movies.contains(movie)) {
            _movies.value = movies.filterNot { it == movie }
        } else {
            _movies.value = movies + movie
        }
    }
}