package com.healthier.healthier.presentation.screens.authentication.sendreset

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

class PasswordResetStateHolder(private val resources: Resources, val navController: NavController) {
    var email by mutableStateOf("")
    var emailError by mutableStateOf(false)

    var error by mutableStateOf("")
    var showError by mutableStateOf(false)

    fun sendPasswordResetEmail(callBack: (email: String,) -> Unit){

        if (emailError or showError) {
            emailError = false
            showError = false
        }

        if (email.isBlank()) {
            if (email.isBlank()) {
                emailError = true
                error = "Enter email"
                return
            }
        }else {
            callBack(email)
        }
    }
}