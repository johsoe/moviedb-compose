package monstarlab.moviedb.presentation.screens.movie

import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.layout.RowScope.weight
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import monstarlab.moviedb.R
import monstarlab.moviedb.model.Movie
import monstarlab.moviedb.presentation.screens.favorites.FavoritesViewModelAmbient
import monstarlab.moviedb.presentation.screens.home.searchButtonAlphaState
import monstarlab.moviedb.presentation.screens.home.searchButtonSizeState
import monstarlab.moviedb.presentation.screens.home.topBarSizeState


@Composable
fun MovieScreen(movie: Movie) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        CoilImageWithCrossfade(
            data = movie.largeUrl,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth().preferredHeight(400.dp)
        )
        Row(
            modifier = Modifier.height(56.dp).background(color = Color.Black)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                movie.title,
                textAlign = TextAlign.Center,
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(color = Color(0x99000000)),
                    fontSize = TextUnit.Sp(26),
                    fontFamily = fontFamily(font(R.font.lobster))
                ),
                modifier = Modifier
                    .weight(5f)
                    .align(alignment = Alignment.CenterVertically)
            )
            FavoriteButton(movie = movie)
        }
        Box(Modifier
            .background(color = Color.White)
            .fillMaxHeight()
        ) {
            Text(
                movie.overview,
                color = Color.DarkGray,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = TextUnit.Sp(12),
                    lineHeight = TextUnit.Sp(18)
                ),
                modifier = Modifier.padding(32.dp)
            )
        }
    }
}

@Composable
fun FavoriteButton(movie: Movie) {
    val favoritesViewModel = FavoritesViewModelAmbient.current
    val isFavorite = favoritesViewModel.movies.observeAsState().value?.contains(movie) == true
    val iconColor = if (isFavorite) Color.White else Color.DarkGray

    IconButton(
        onClick = {
            favoritesViewModel.toggleMovie(movie)
        },
        modifier = Modifier
            .weight(1f)
            .align(alignment = Alignment.CenterVertically)
            .padding(bottom = 4.dp)
    ) {
        Icon(Icons.Filled.Star, tint = iconColor)
    }
}