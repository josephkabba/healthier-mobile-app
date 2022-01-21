package com.healthier.healthier.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.healthier.healthier.R
import com.healthier.healthier.presentation.common.Circle
import com.healthier.healthier.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthierTheme(darkTheme = false) {
                LaunchScreen()
            }
        }

        val intent = Intent(this, Authentication::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        lifecycleScope.launch {
            delay(3000)
            startActivity(intent)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    LaunchScreen()
}

@Composable
fun LaunchScreen(){
    val padding: Dp = 16.dp

    Circle (color = lightGreen.copy(alpha = 0.6F),
        size = 176.dp, modifier = Modifier.offset((-80).dp, (-10).dp))

    Circle (color = lightGreen.copy(alpha = 0.6F),
        size = 176.dp, modifier = Modifier.offset((-15).dp, (-80).dp))

    Column(horizontalAlignment = Alignment.CenterHorizontally, 
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(padding)) {
        
        Image(painter = painterResource(id = R.drawable.work_out),
            contentDescription = "workout_starter_image",
            modifier = Modifier
                .width(467.dp)
                .height(360.dp)
                .padding(padding))

        Poster(padding = padding)
    }
}

@Composable
fun Poster(padding: Dp){
    Card (backgroundColor = healthierGreen, elevation = 20.dp, shape = HealthierTheme.shapes.shapes.medium)  {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)){

            Text(text = stringResource(R.string.app_name), style = HealthierTheme.typography.typography.body2, color = Color.White)
            Text(text = stringResource(R.string.welcome_message), color = Color.White)
        }
    }
}