package com.healthier.healthier.presentation.navigation

sealed class Screen(val route: String){
    object LoginScreen: Screen("login_screen")
    object SignUpScreen: Screen("signup_screen")
}
