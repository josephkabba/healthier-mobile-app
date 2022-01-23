package com.healthier.healthier.presentation.screens.authentication.sendreset

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
private fun rememberPasswordResetState(
    resources: Resources = LocalContext.current.resources,
    navController: NavController
) = remember {
    PasswordResetStateHolder(resources, navController)
}

@Composable
fun PasswordReset(passwordResetViewModel: PasswordResetViewModel = viewModel(), navController: NavController){

    val passwordResetState = rememberPasswordResetState(navController = navController)

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = "Reset Password", fontWeight = FontWeight.Bold, fontSize = 28.sp)

            IconButton(content = {
                Image(painter = painterResource(id = R.drawable.ic_close_24), contentDescription = "close password reset")
            },onClick = {
                navController.navigateUp()
            })
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        CustomTextField(
            value = passwordResetState.email,
            isError = passwordResetState.emailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = "Email"
        ) {
            passwordResetState.email = it
        }

        if (passwordResetState.showError or passwordResetState.emailError) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )

            Card(
                backgroundColor = Color.Red,
                elevation = 20.dp,
                shape = HealthierTheme.shapes.shapes.medium
            ) {
                Text(
                    color = Color.White, text = passwordResetState.error, modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
            }
        }

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            CustomButton(title = "Send reset email") {
                passwordResetState.sendPasswordResetEmail { email ->
                    passwordResetViewModel.sendPasswordResetEmail(email)
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

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

            ClickableText(modifier = Modifier.fillMaxWidth(),
                style = TextStyle(textAlign = TextAlign.Center),
                text = annotatedString,
                onClick = {

                })

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(23.dp)
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun PasswordResetPreview() {
//    PasswordReset()
//}