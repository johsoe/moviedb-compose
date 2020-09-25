package monstarlab.moviedb.presentation.screens.favorites

import androidx.compose.animation.transition
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.layout.RowScope.weight
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import monstarlab.moviedb.R
import monstarlab.moviedb.presentation.screens.home.*
import monstarlab.moviedb.presentation.screens.home.HomeTop
import monstarlab.moviedb.presentation.screens.movie.MovieList

@Preview
@Composable
fun FavoritesScreen() {
    val favoritesViewModel = FavoritesViewModelAmbient.current
    val movies = favoritesViewModel.movies.observeAsState(emptyList())
    Column(
        Modifier
        .background(color = Color(0xFFf8f8f8))
        .fillMaxHeight()) {
            FavoritesTop()
            MovieList(movies.value)
    }
}

@Composable
fun FavoritesTop() {
    Row(
        modifier = Modifier.height(92.dp).padding(top = 20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            "Favorites",
            textAlign = TextAlign.Center,
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
        Spacer(modifier = Modifier.weight(1f))
    }
}