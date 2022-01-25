package com.healthier.healthier.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.healthier.healthier.presentation.screens.authentication.login.Login
import com.healthier.healthier.presentation.navigation.Screen
import com.healthier.healthier.presentation.screens.authentication.sendreset.PasswordReset
import com.healthier.healthier.presentation.screens.authentication.signup.SignUp
import com.healthier.healthier.ui.theme.HealthierTheme
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.healthier.healthier.presentation.common.childEnterTransition
import com.healthier.healthier.presentation.common.childPopExitTransition
import com.healthier.healthier.presentation.common.parentExitTransition
import com.healthier.healthier.presentation.common.parentPopEnterTransition


class Authentication : AppCompatActivity() {


    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthierTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {

                        composable(route = Screen.LoginScreen.route, exitTransition = { ->
                            parentExitTransition()
                        }, popEnterTransition = { ->
                            parentPopEnterTransition()
                        }) {
                            Login(navController = navController)
                        }

                        composable(route = Screen.SignUpScreen.route, enterTransition = {
                            childEnterTransition()
                        }, popExitTransition = {
                            childPopExitTransition()
                        }) {
                            SignUp(navController = navController)
                        }

                        composable(route = Screen.PasswordResetScreen.route, enterTransition = {
                            childEnterTransition()
                        }, popExitTransition = {
                            childPopExitTransition()
                        }) {
                            PasswordReset(navController = navController)
                        }
                    }
                }
            }
        }

    }
}