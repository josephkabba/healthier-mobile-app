package com.healthier.healthier.presentation.screens.main.account


import android.view.RoundedCorner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.healthier.healthier.R
import com.healthier.healthier.presentation.common.CustomButton
import com.healthier.healthier.ui.theme.healthierGray
import com.healthier.healthier.ui.theme.healthierGreen

@Composable
fun Account(navController: NavController, accountViewModel: AccountViewModel = viewModel()){
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Account")
        }, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_settings_24), contentDescription = "settings")
            }
        },
            elevation = 4.dp,
            backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onSecondary)
    }) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(30.dp))

            Image(painter = painterResource(id = R.drawable.ic_settings_24),
                contentDescription = "profile image",
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = healthierGreen,
                        shape = RoundedCornerShape(percent = 100)
                    )
                    .background(
                        color = healthierGray,
                        shape = RoundedCornerShape(percent = 100)
                    )
                    .width(132.dp)
                    .height(132.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))
            
            Card(elevation = 3.dp,
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(192.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize()) {

                    ClickableText(text = AnnotatedString("Change profile"), style = TextStyle(fontWeight = FontWeight.Light, fontSize = 28.sp), modifier = Modifier.padding(top = 10.dp), onClick = {
                        //TODO
                        //click function
                    })

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(healthierGray)
                        .clip(
                            RoundedCornerShape(5.dp)
                        ))

                    ClickableText(text = AnnotatedString("Your Data"), style = TextStyle(fontWeight = FontWeight.Light, fontSize = 28.sp), onClick = {
                        //TODO
                        //click function
                    })

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(healthierGray)
                        .clip(
                            RoundedCornerShape(5.dp)
                        ))

                    ClickableText(text = AnnotatedString("Contact us"), style = TextStyle(fontWeight = FontWeight.Light, fontSize = 28.sp), modifier = Modifier.padding(bottom = 10.dp), onClick = {
                        //TODO
                        //click function
                    })

                }
            }

            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                CustomButton(title = "Sign out", color = Color.Red, modifier = Modifier.fillMaxWidth()) {
                    //TODO
                    //click function
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountPreview(){
    Account(navController = rememberNavController(), accountViewModel = AccountViewModel())
}