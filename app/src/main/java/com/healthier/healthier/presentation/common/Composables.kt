package com.healthier.healthier.presentation.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.healthier.healthier.R
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
fun Notification(name: String,
                 date: String,
                 detail: String,
){
    val expanded = remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text(text = name, fontSize = 24.sp)
            Text(text = date, fontSize = 14.sp, fontWeight = FontWeight.Light)
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxWidth()) {
            ExpandableText(text = detail, isExpanded = expanded)

            if (expanded.value){
                Icon(painter = painterResource(id = R.drawable.ic_arrow_up_24), contentDescription = "minimise")
            }else {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_down_24), contentDescription = "expand")
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(healthierGray)
            .clip(
                RoundedCornerShape(5.dp)
            ))
    }
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
            errorTrailingIconColor = Color.Red,
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
            defaultElevation = 6.dp,
            pressedElevation = 1.dp,
            disabledElevation = 0.dp
        ),
        content = {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    })
}

@Composable
fun ExpandableText(modifier: Modifier = Modifier, text: String, isExpanded: MutableState<Boolean>){
    val MINIMIZED_MAX_LINES = 3

    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    var finalText by remember { mutableStateOf(text) }

    Text(
        text = finalText,
        maxLines = if (isExpanded.value) Int.MAX_VALUE else MINIMIZED_MAX_LINES,
        onTextLayout = { textLayoutResultState.value = it },
        modifier = modifier
            .clickable(enabled = isClickable) { isExpanded.value = !isExpanded.value }
            .animateContentSize(),
    )

    val textLayoutResult = textLayoutResultState.value

    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded.value -> {
                finalText = text
            }
            !isExpanded.value && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(MINIMIZED_MAX_LINES - 1)
                val showMoreString = "..."
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.' }

                finalText = "$adjustedText$showMoreString"

                isClickable = true
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewComposable(){
    Notification(name = "name", date = "date", detail = "this is a notification to notify you about notifications that notify you about information ok ok")
}