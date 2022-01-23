package com.healthier.healthier.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.healthier.healthier.presentation.screens.authentication.login.Login
import com.healthier.healthier.presentation.navigation.Screen
import com.healthier.healthier.presentation.screens.authentication.sendreset.PasswordReset
import com.healthier.healthier.presentation.screens.authentication.signup.SignUp
import com.healthier.healthier.ui.theme.HealthierTheme
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


class Authentication : AppCompatActivity() {


    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthierTheme(darkTheme = false) {
                val navController = rememberAnimatedNavController()
                AnimatedNavHost(navController = navController, startDestination = Screen.LoginScreen.route){

                    composable(route = Screen.LoginScreen.route, exitTransition = { ->
                        slideOutHorizontally(
                            targetOffsetX = { -300 },
                            animationSpec = tween(
                                durationMillis = 300,
                            )
                        ) +
                        fadeOut(animationSpec = tween(300))
                    }, popEnterTransition = { ->
                        slideInHorizontally(
                            initialOffsetX = { 300 },
                            animationSpec = tween(
                                durationMillis = 300,
                            )
                        ) +
                        fadeIn(animationSpec = tween(300))
                    }){
                        Login(navController = navController)
                    }

                    composable(route = Screen.SignUpScreen.route){
                        SignUp(navController = navController)
                    }

                    composable(route = Screen.PasswordResetScreen.route){
                        PasswordReset(navController = navController)
                    }
                }
            }
        }

    }
}