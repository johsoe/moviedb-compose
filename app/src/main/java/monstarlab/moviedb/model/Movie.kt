package monstarlab.moviedb.model


data class Movie(
        val id : Int = 0,
        val title: String = "Matrix",
        val posterUrl: String = "https://loremflickr.com/300/300",
        val thumbUrl: String = "",
        val largeUrl: String = "",
        val overview: String = "",
)