package com.healthier.healthier.presentation.screens.authentication.login

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.healthier.healthier.R
import com.healthier.healthier.presentation.common.CustomButton
import com.healthier.healthier.presentation.common.CustomTextField
import com.healthier.healthier.presentation.navigation.Screen
import com.healthier.healthier.ui.theme.*

@Composable
private fun rememberLoginState(
    resources: Resources = LocalContext.current.resources,
    navController: NavController
) = remember {
    LoginStateHolder(resources, navController)
}

@Composable
fun Login(loginViewModel: LoginViewModel = viewModel(), navController: NavController){

    val loginState = rememberLoginState(navController = navController)

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
                navController.navigate(Screen.SignUpScreen.route)
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
                if (loginState.showPassword){
                    IconButton(content = {
                        Image(painter = painterResource(id = R.drawable.ic_visibility_off_24), contentDescription = "eye")
                    }, onClick = {
                        loginState.showPassword = false
                    })
                }else {
                    IconButton(content = {
                        Image(painter = painterResource(id = R.drawable.ic_eye_24), contentDescription = "eye")
                    }, onClick = {
                        loginState.showPassword = true
                    })
                }
            },
            visualTransformation = if (loginState.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = "Password") {
            loginState.password = it
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            ClickableText(text = AnnotatedString("Forgot your password ?"), style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 16.sp, color = healthierBlue
            )) {
               navController.navigate(Screen.PasswordResetScreen.route)
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
                .height(20.dp))

            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = healthierGray, fontStyle = FontStyle.Normal)) {
                    append(text = "By using this app you agree to healthier' s ")

                    pushStringAnnotation(tag = "terms", annotation = "")
                    withStyle(style = SpanStyle(color = healthierBlue)) {
                        append(text = "Terms and Conditions")
                    }

                    append(text = " and ")

                    pushStringAnnotation(tag = "policy", annotation = "")
                    withStyle(style = SpanStyle(color = healthierBlue)) {
                        append(text = "privacy policy")
                    }

                    append(text = ".")

                    pop()
                }
            }

            ClickableText(modifier = Modifier.fillMaxWidth(), style = TextStyle(textAlign = TextAlign.Center),
                text = annotatedString, onClick = {

            })

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(23.dp))
        }

    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun LoginPreview() {
//    Login()
//}