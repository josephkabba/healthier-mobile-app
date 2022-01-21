package com.healthier.healthier.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.healthier.healthier.presentation.screens.authentication.login.Login
import com.healthier.healthier.presentation.navigation.Screen
import com.healthier.healthier.presentation.screens.authentication.signup.SignUp
import com.healthier.healthier.ui.theme.HealthierTheme

class Authentication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthierTheme() {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
                    composable(route = Screen.LoginScreen.route){
                        Login()
                    }

                    composable(route = Screen.SignUpScreen.route){
                        SignUp()
                    }
                }
            }
        }

    }
}