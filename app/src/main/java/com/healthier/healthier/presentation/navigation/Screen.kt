package com.healthier.healthier.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.healthier.healthier.R

sealed class Screen(val route: String, @DrawableRes val icon: Int = 0, val name: String = ""){

    //authentication
    object LoginScreen: Screen("login_screen")
    object SignUpScreen: Screen("signup_screen")
    object PasswordResetScreen: Screen("password_reset_screen")

    //main
    object HomeScreen: Screen("home_screen", icon = R.drawable.ic_home_24, name = "Home")
    object AccountScreen: Screen("account_screen", icon = R.drawable.ic_account_24, name = "Account")
    object MealsScreen: Screen("meals_screen", icon = R.drawable.ic_fastfood_24, name = "Meals")
    object FitnessScreen: Screen("workout_screen", icon = R.drawable.ic_fitness_24, name = "Fitness")

}
