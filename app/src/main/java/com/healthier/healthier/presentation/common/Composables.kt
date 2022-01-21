package com.healthier.healthier.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.healthier.healthier.ui.theme.*

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
fun CustomTextField(modifier: Modifier = Modifier,
                    enabled: Boolean = true,
                    keyboardOptions: KeyboardOptions = KeyboardOptions(),
                    textFieldDefaults: TextFieldDefaults = TextFieldDefaults,
                    isError: Boolean = false,
                    visualTransformation: VisualTransformation = VisualTransformation.None,
                    value: String,
                    label: String,
                    trailingIcon: @Composable (() -> Unit) = {},
                    onValueChange: (String) -> Unit) {

    TextField(value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        enabled = enabled,
        trailingIcon = trailingIcon,
        isError = isError,
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        },

        colors = textFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = healthierGreen,
            focusedLabelColor = healthierBlue,
            errorLabelColor = Color.Red,
            errorCursorColor = Color.Red,
            errorIndicatorColor = Color.Red,
            focusedIndicatorColor = healthierBlue
        ))
}


@Composable
fun CustomButton(modifier: Modifier = Modifier,
                 title: String,
                 enabled:Boolean = true,
                 color: Color = healthierGreen,
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
        modifier = modifier
            .fillMaxWidth()
            .height(51.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 5.dp,
            disabledElevation = 0.dp
        ),
        content = {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
    })
}