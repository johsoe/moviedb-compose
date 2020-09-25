package monstarlab.moviedb.domain.network.pojos

data class MovieResultDto(
    val page: Int,
    val results: List<MovieDto>
)