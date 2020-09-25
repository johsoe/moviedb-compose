package monstarlab.moviedb.presentation.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticAmbientOf

val NavigationAmbient = staticAmbientOf { NavigationViewModel() }

class NavigationViewModel {
    val currentScreen = mutableStateOf<Screens>(Screens.Home)

    val canGoBack: Boolean
        get() = currentScreen.value != Screens.Home

    fun changeScreen(screen: Screens) {
        currentScreen.value = screen
    }

    fun pop() {
        currentScreen.value = Screens.Home
    }
}