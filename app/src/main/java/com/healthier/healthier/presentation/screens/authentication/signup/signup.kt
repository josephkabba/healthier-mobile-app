package com.healthier.healthier.presentation.screens.authentication.signup

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
import com.healthier.healthier.ui.theme.HealthierTheme
import com.healthier.healthier.ui.theme.healthierBlue
import com.healthier.healthier.ui.theme.healthierGray

@Composable
private fun rememberSignUpState(
    resources: Resources = LocalContext.current.resources,
    navController: NavController
) = remember {
    SignUpStateHolder(resources, navController)
}

@Composable
fun SignUp(signUpViewModel: SignUpViewModel = viewModel(), navController: NavController){

    val signUpState = rememberSignUpState(navController = navController)

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(25.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

            Text(text = "Sign Up", fontWeight = FontWeight.Bold, fontSize = 28.sp)

            IconButton(content = {
                Image(painter = painterResource(id = R.drawable.ic_close_24), contentDescription = "close sign up")
            },onClick = {
                navController.navigateUp()
            })

        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        CustomTextField(value = signUpState.name,
            isError = signUpState.nameError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = "Name") {
            signUpState.name = it
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))

        CustomTextField(value = signUpState.email,
            isError = signUpState.emailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = "Email") {
            signUpState.email = it
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))

        CustomTextField(value = signUpState.password,
            isError = signUpState.passwordError,
            trailingIcon = {
                if (signUpState.showPassword){
                    IconButton(content = {
                        Image(painter = painterResource(id = R.drawable.ic_visibility_off_24), contentDescription = "eye")
                    }, onClick = {
                        signUpState.showPassword = false
                    })
                }else {
                    IconButton(content = {
                        Image(painter = painterResource(id = R.drawable.ic_eye_24), contentDescription = "eye")
                    }, onClick = {
                        signUpState.showPassword = true
                    })
                }
            },
            visualTransformation = if (signUpState.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = "Password") {
            signUpState.password = it
        }

        if (signUpState.showError or signUpState.passwordError or signUpState.nameError or signUpState.emailError){
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(30.dp))

            Card (backgroundColor = Color.Red, elevation = 20.dp, shape = HealthierTheme.shapes.shapes.medium)  {
                Text(color = Color.White, text = signUpState.error, modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp))
            }
        }

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            CustomButton(title = "Sign up") {
                signUpState.signInUser { name, email, password ->
                    signUpViewModel.signUpUser(name, email, password)
                }
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp))

            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = healthierGray, fontStyle = FontStyle.Normal)) {
                    append(text = "By signing up you agree to healthier' s ")

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

//@Preview(showBackground = true)
//@Composable
//private fun SignUpPreview() {
//    SignUp()
//}