package monstarlab.moviedb.presentation.screens.movie

import androidx.compose.foundation.Box
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.layout.RowScope.weight
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import monstarlab.moviedb.model.Movie
import monstarlab.moviedb.presentation.navigation.NavigationAmbient
import monstarlab.moviedb.presentation.navigation.Screens
import monstarlab.moviedb.presentation.screens.home.HomeViewModelAmbient


@Composable
fun MovieList(movies: List<Movie>) {
    val navigationViewModel = NavigationAmbient.current
    val chunked = movies.chunked(2)
    LazyColumnFor(items = chunked, modifier = Modifier.fillMaxHeight()) { subList ->
        Row(
            Modifier.fillMaxWidth()
        ) {
            subList.forEach { movie ->
                Box(
                    modifier = Modifier.padding(24.dp).weight(1f).clickable(onClick = {
                        navigationViewModel.changeScreen(Screens.MovieScreen(movie))
                    })
                ) {
                    Surface(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.preferredHeight(200.dp).preferredWidth(133.dp).align(
                            Alignment.CenterHorizontally)
                    ) {
                        CoilImageWithCrossfade(
                            data = movie.thumbUrl,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .preferredHeight(200.dp)
                                .preferredWidth(133.dp)
                        )
                    }
                }
            }
        }
    }
}