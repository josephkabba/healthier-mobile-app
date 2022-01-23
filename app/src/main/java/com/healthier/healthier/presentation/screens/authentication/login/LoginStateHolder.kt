package com.healthier.healthier.presentation.screens.authentication.login

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

class LoginStateHolder (private val resources: Resources, val navController: NavController) {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var error by mutableStateOf("")
    var showError by mutableStateOf(false)

    var passwordError by mutableStateOf(false)
    var emailError by mutableStateOf(false)

    var showPassword by mutableStateOf(false)

    fun signInUser(callBack: (email: String, password: String) -> Unit){

        if (emailError or passwordError or showError) {
            emailError = false
            passwordError = false
            showError = false
        }

        if (email.isBlank() or password.isBlank()) {
            if (email.isBlank()) {
                emailError = true
                error = "Enter email"
                return
            }

            if (password.isBlank()) {
                passwordError = true
                error = "Enter password"
                return
            }
        }else {
            callBack(email, password)
        }
    }
}