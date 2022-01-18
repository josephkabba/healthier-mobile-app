package com.healthier.healthier.presentation.screens.authentication.login

import android.content.res.Resources
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.healthier.healthier.presentation.common.CustomButton
import com.healthier.healthier.presentation.common.CustomTextField

class LoginViewModel: ViewModel(){

    fun loginUser(email: String, password: String){

    }
}

private class LoginFormState (private val resources: Resources) {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var error by mutableStateOf("")
    var showError by mutableStateOf(false)

    fun signInUser(callBack: (email: String, password: String) -> Unit){
        if (showError) {
            showError = false
        }

        if (email.isBlank() or password.isBlank()) {
            if (email.isBlank()) {
                showError = true
                error = "Enter email"
            }

            if (email.isBlank()) {
                showError = true
                error = "Enter password"
            }
        }else {
            callBack(email, password)
        }
    }
}

@Composable
private fun rememberLoginState(
    resources: Resources = LocalContext.current.resources
) = rememberSaveable {
    LoginFormState(resources)
}

@Composable
fun Login(loginViewModel: LoginViewModel){

    val loginState = rememberLoginState()

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {

        CustomTextField(value = loginState.email, label = "Email") {
            loginState.email = it
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))

        CustomTextField(value = loginState.password, label = "Password") {
            loginState.password = it
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))

        if (loginState.showError){
            Text(color = Color.Red, text = loginState.error, modifier = Modifier.fillMaxWidth())
        }

        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom) {
            CustomButton(title = "Login") {
                loginState.signInUser { email, password ->
                    loginViewModel.loginUser(email, password)
                }
            }
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//private fun LoginPreview() {
//    Login()
//}