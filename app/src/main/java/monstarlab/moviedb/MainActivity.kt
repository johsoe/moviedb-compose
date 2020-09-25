package monstarlab.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import monstarlab.moviedb.presentation.MainScreen
import monstarlab.moviedb.presentation.navigation.NavigationAmbient
import monstarlab.moviedb.presentation.navigation.NavigationViewModel
import monstarlab.moviedb.presentation.base.MovieDBTheme
import monstarlab.moviedb.presentation.screens.favorites.FavoritesViewModel
import monstarlab.moviedb.presentation.screens.favorites.FavoritesViewModelAmbient
import monstarlab.moviedb.presentation.screens.home.HomeViewModel
import monstarlab.moviedb.presentation.screens.home.HomeViewModelAmbient

class MainActivity : AppCompatActivity() {

    private val navigationViewModel = NavigationViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDBTheme {
                Providers(
                    NavigationAmbient provides navigationViewModel,
                    HomeViewModelAmbient provides HomeViewModel(),
                    FavoritesViewModelAmbient provides FavoritesViewModel()
                ) {
                    MainScreen()
                }
            }
        }
    }


    override fun onBackPressed() {
        if(navigationViewModel.canGoBack) {
            navigationViewModel.pop()
        } else {
            super.onBackPressed()
        }
    }
}