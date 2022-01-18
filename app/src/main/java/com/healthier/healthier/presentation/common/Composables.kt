package com.healthier.healthier.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Circle(modifier: Modifier = Modifier, color: Color, size: Dp = 32.dp) {
    Box(
        modifier = modifier
            .requiredSize(size)
            .clip(CircleShape)
            .background(color)

    )
}

@Composable
fun CustomTextField(modifier: Modifier = Modifier, value: String, label: String, onValueChange: (String) -> Unit) {

    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        })
}

@Composable
fun CustomButton(modifier: Modifier = Modifier,
                 title: String,
                 enabled:Boolean = true,
                 color: Color = Color.Green,
                 onClick: () -> Unit){

    Button(onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = Color.LightGray,
            disabledContentColor = Color.White,
            backgroundColor = color,
            contentColor = Color.White
        ),
        modifier = modifier.fillMaxWidth(),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 15.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        content = {
        Text(text = title, fontWeight = FontWeight.Bold)
    })
}