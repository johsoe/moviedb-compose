package monstarlab.moviedb.presentation.navigation

import monstarlab.moviedb.model.Movie

sealed class Screens {
    object Home: Screens()
    object Favorites: Screens()
    data class MovieScreen(val movie: Movie): Screens()
}