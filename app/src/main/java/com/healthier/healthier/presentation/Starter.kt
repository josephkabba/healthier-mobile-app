package com.healthier.healthier.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.healthier.healthier.R
import com.healthier.healthier.ui.theme.HealthierTheme
import com.healthier.healthier.ui.theme.Shapes
import com.healthier.healthier.ui.theme.primary
import java.time.format.TextStyle


class Starter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthierTheme {
                Screen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    Screen()
}

@Composable
fun Screen(padding: Dp = 16.dp){

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(padding)) {

        Card (backgroundColor = primary, shape = HealthierTheme.shapes.shapes.medium)  {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)){

                Text(text = stringResource(R.string.app_name), style = HealthierTheme.typography.typography.body2, color = HealthierTheme.colors.light.primary)
                Text(text = stringResource(R.string.welcome_message), color = HealthierTheme.colors.light.primary)
            }
        }
    }
}