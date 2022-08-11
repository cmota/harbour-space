package com.harbourspace.unsplash.compose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.harbourspace.unsplash.R

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val stringResId: Int,
    @DrawableRes val drawResId: Int
) {
    object Home : BottomNavigationScreens("Home", R.string.main_navigation_home, R.drawable.ic_home)
    object Favourites : BottomNavigationScreens("Favourites", R.string.main_navigation_favourites, R.drawable.ic_favourite)
    object About : BottomNavigationScreens("About", R.string.main_navigation_about, R.drawable.ic_info)
}
