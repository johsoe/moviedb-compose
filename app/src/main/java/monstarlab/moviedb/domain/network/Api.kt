package monstarlab.moviedb.domain.network

import monstarlab.moviedb.domain.network.pojos.MovieDto
import monstarlab.moviedb.domain.network.pojos.MovieResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("api_key") key: String = "a33bb60d9260b17fe481f1070a6b4f7e",
        @Query("primary_release_year") primaryReleaseYear: Int = 2020
    ): Response<MovieResultDto>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") searchText: String,
        @Query("api_key") key: String = "a33bb60d9260b17fe481f1070a6b4f7e"
    ): Response<MovieResultDto>

}