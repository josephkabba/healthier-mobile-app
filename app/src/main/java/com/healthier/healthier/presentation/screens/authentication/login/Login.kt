package com.healthier.healthier.presentation.screens.authentication.login

import android.content.res.Resources
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.healthier.healthier.R
import com.healthier.healthier.presentation.common.CustomButton
import com.healthier.healthier.presentation.common.CustomTextField
import com.healthier.healthier.ui.theme.HealthierTheme
import com.healthier.healthier.ui.theme.healthierBlue
import com.healthier.healthier.ui.theme.healthierGreen
import com.healthier.healthier.ui.theme.primary

class LoginViewModel: ViewModel(){

    fun loginUser(email: String, password: String){

    }
}

private class LoginFormState (private val resources: Resources) {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var error by mutableStateOf("")
    var showError by mutableStateOf(false)

    var passwordError by mutableStateOf(false)
    var emailError by mutableStateOf(false)

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

@Composable
private fun rememberLoginState(
    resources: Resources = LocalContext.current.resources
) = remember {
    LoginFormState(resources)
}

@Composable
fun Login(loginViewModel: LoginViewModel = viewModel()){

    val loginState = rememberLoginState()

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(25.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

            Text(text = "Login", fontWeight = FontWeight.Bold, fontSize = 28.sp)

            ClickableText(text = AnnotatedString("Sign up"), style = TextStyle(
                fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = healthierBlue
            )) {

            }
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        CustomTextField(value = loginState.email,
            isError = loginState.emailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = "Email") {
            loginState.email = it
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))

        CustomTextField(value = loginState.password,
            isError = loginState.passwordError,
            trailingIcon = {
                Image(painter = painterResource(id = R.drawable.ic_eye_24), contentDescription = "eye")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = "Password") {
            loginState.password = it
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            ClickableText(text = AnnotatedString("Forgot your password ?"), style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 16.sp, color = healthierGreen
            )) {

            }
        }

        if (loginState.showError or loginState.passwordError or loginState.emailError){
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(30.dp))

            Card (backgroundColor = Color.Red, elevation = 20.dp, shape = HealthierTheme.shapes.shapes.medium)  {
                Text(color = Color.White, text = loginState.error, modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp))
            }
        }

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            CustomButton(title = "Login") {
                loginState.signInUser { email, password ->
                    loginViewModel.loginUser(email, password)
                }
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(0.dp))


            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(43.dp))
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginPreview() {
    Login()
}