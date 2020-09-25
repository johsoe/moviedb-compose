package monstarlab.moviedb.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import monstarlab.moviedb.domain.network.ApiModule
import monstarlab.moviedb.domain.network.pojos.mapMovie
import monstarlab.moviedb.model.Movie
import java.lang.Exception

class SearchMoviesUseCase {

    suspend fun search(searchText: String): List<Movie> {
        return try {
            val moviesResult = withContext(Dispatchers.IO) { ApiModule.api.searchMovies(searchText) }
            return if(moviesResult.isSuccessful) {
                val movs = moviesResult.body()?.results ?: emptyList()
                movs.map { it.mapMovie() }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

}