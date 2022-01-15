package com.healthier.healthier.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.healthier.healthier.ui.theme.HealthierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthierTheme {
                 // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.primary) {
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting(name: String = "kabba") {
    Text(text = "Hello $name!")
}

