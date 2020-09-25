package monstarlab.moviedb.presentation.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import monstarlab.moviedb.R
import monstarlab.moviedb.presentation.navigation.NavigationAmbient
import monstarlab.moviedb.presentation.navigation.Screens
import monstarlab.moviedb.presentation.screens.movie.MovieList

@Preview
@Composable
fun HomeScreen() {
    val homeViewModel = HomeViewModelAmbient.current
    val movies = remember { homeViewModel.movies }

    Column(
        Modifier
            .background(color = Color(0xFFf8f8f8))
            .fillMaxHeight()
    ) {
        HomeTop()
        Crossfade(current = homeViewModel.isLoading.value) { isLoading ->
                if(isLoading) {
                    HomeLoading()
                } else {
                    MovieList(movies.value)
                }
        }
    }
}

val searchButtonSizeState = DpPropKey()
val searchButtonAlphaState = FloatPropKey()
val searchContentSizeState = DpPropKey()
val searchContentAlphaState = FloatPropKey()
val topBarSizeState = DpPropKey()

enum class SearchState {
    Visible, Hidden
}

private val searchTransitionDefinition = transitionDefinition<SearchState> {
    state(SearchState.Visible) {
        this[searchButtonSizeState] = 0.dp
        this[searchButtonAlphaState] = 0f
        this[searchContentSizeState] = 56.dp
        this[searchContentAlphaState] = 1f
        this[topBarSizeState] = 64.dp
    }
    state(SearchState.Hidden) {
        this[searchButtonSizeState] = 48.dp
        this[searchButtonAlphaState] = 1f
        this[searchContentSizeState] = 0.dp
        this[searchContentAlphaState] = 0f
        this[topBarSizeState] = 92.dp
    }

    transition(fromState = SearchState.Hidden, toState = SearchState.Visible) {
        searchButtonSizeState using tween(durationMillis = 200)
        searchButtonAlphaState using tween(durationMillis = 200)
        searchContentSizeState using tween(durationMillis = 200)
        searchContentAlphaState using tween(durationMillis = 200)
        topBarSizeState using tween(durationMillis = 200)
    }
    transition(fromState = SearchState.Visible, toState = SearchState.Hidden) {
        searchButtonSizeState using tween(durationMillis = 200)
        searchButtonAlphaState using tween(durationMillis = 200)
        searchContentSizeState using tween(durationMillis = 200)
        searchContentAlphaState using tween(durationMillis = 200)
        topBarSizeState using tween(durationMillis = 200)
    }
}

@Composable
fun HomeLoading() {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight().fillMaxWidth().align(Alignment.CenterHorizontally)) {
        CircularProgressIndicator(
            color = Color.DarkGray,
            modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .preferredHeight(20.dp)
            .preferredWidth(20.dp)
        )
    }
}

@Composable
fun HomeTop() {
    val searchVisible = remember { mutableStateOf(false) }
    val state = transition(
        definition = searchTransitionDefinition,
        initState = SearchState.Hidden,
        toState = if (searchVisible.value) SearchState.Visible else SearchState.Hidden
    )
    val homeViewModel = HomeViewModelAmbient.current

    Row(
        modifier = Modifier.height(state[topBarSizeState]).padding(top = 20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            "Fancy Movie DB",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                shadow = Shadow(color = Color(0x99000000)),
                fontSize = TextUnit.Companion.Sp(26),
                fontFamily = fontFamily(font(R.font.lobster))
            ),
            modifier = Modifier
                .weight(5f)
                .align(alignment = Alignment.CenterVertically)
        )
        IconButton(
            onClick = {
                searchVisible.value = !searchVisible.value
            },
            modifier = Modifier
                .weight(1f)
                .preferredSize(state[searchButtonSizeState])
                .drawOpacity(state[searchButtonAlphaState])
                .align(alignment = Alignment.CenterVertically)
                .padding(bottom = 4.dp)
        ) {
            Icon(Icons.Filled.Search)
        }
    }

    Row(
        modifier = Modifier
            .preferredHeight(state[searchContentSizeState])
            .drawOpacity(state[searchContentAlphaState]),
    ) {
        Spacer(Modifier.weight(1f))
        TextField(
            value = homeViewModel.searchText.value,
            placeholder = {
                Text("Search for movies...")
            },
            modifier = Modifier
                .weight(5f)
                .align(alignment = Alignment.CenterVertically),
            onValueChange = {
                homeViewModel.updateSearchText(it)
            }
        )
        IconButton(
            onClick = {
                homeViewModel.clearedSearch()
                searchVisible.value = !searchVisible.value
            },
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically)
        ) {
            Icon(Icons.Filled.Close)
        }
    }
}