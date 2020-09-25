package monstarlab.moviedb.presentation

import android.view.View
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.platform.ViewAmbient
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.ui.tooling.preview.Preview
import monstarlab.moviedb.presentation.navigation.NavigationAmbient
import monstarlab.moviedb.presentation.navigation.Screens
import monstarlab.moviedb.presentation.screens.favorites.FavoritesScreen
import monstarlab.moviedb.presentation.screens.home.HomeScreen
import monstarlab.moviedb.presentation.screens.movie.MovieScreen

@Preview
@Composable
fun MainScreen() {
    val navigationViewModel = NavigationAmbient.current
    val screens = navigationViewModel.currentScreen
    val bottomPadding = remember { mutableStateOf(20.dp) }
    val density = DensityAmbient.current.density
    val view = ViewAmbient.current
    view.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    ViewCompat.setOnApplyWindowInsetsListener(view) { v: View, insets: WindowInsetsCompat ->
        println(insets)
        println(density)
        println(insets.systemWindowInsetBottom)
        val convertedPx = insets.stableInsetBottom / density
        bottomPadding.value = convertedPx.toInt().dp
        insets.consumeSystemWindowInsets()
    }

    Scaffold(
        modifier = Modifier.padding(bottom = bottomPadding.value),
        bodyContent = {
            Crossfade(current = screens.value) { screen ->
                when (screen) {
                    is Screens.Home -> HomeScreen()
                    is Screens.Favorites -> FavoritesScreen()
                    is Screens.MovieScreen -> MovieScreen(movie = screen.movie)
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.White,
                elevation = 8.dp
            ) {
                IconButton(
                    onClick = {
                        navigationViewModel.changeScreen(Screens.Home)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Filled.Home)
                }
                IconButton(
                    onClick = {
                        navigationViewModel.changeScreen(Screens.Favorites)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Filled.Favorite)
                }
            }
        }
    )
}