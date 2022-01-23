package com.healthier.healthier.presentation.screens.authentication.signup

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

class SignUpStateHolder (private val resources: Resources, val navController: NavController) {
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var error by mutableStateOf("")
    var showError by mutableStateOf(false)

    var passwordError by mutableStateOf(false)
    var emailError by mutableStateOf(false)
    var nameError by mutableStateOf(false)

    var showPassword by mutableStateOf(false)

    fun signInUser(callBack: (name: String, email: String, password: String) -> Unit){
        if (emailError or passwordError or showError or nameError) {
            emailError = false
            nameError = false
            passwordError = false
            showError = false
        }

        if (email.isBlank() or password.isBlank() or name.isBlank()) {
            if (name.isBlank()) {
                showError = true
                error = "Enter name"
                return
            }

            if (email.isBlank()) {
                showError = true
                error = "Enter email"
                return
            }

            if (password.isBlank()) {
                showError = true
                error = "Enter password"
                return
            }
        }else {
            callBack(name, email, password)
        }
    }
}