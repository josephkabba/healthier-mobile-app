package com.healthier.healthier.presentation.screens.main.meals

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.healthier.healthier.R
import com.healthier.healthier.presentation.common.MealCard
import com.healthier.healthier.ui.theme.healthierGreen

@Composable
fun rememberMealsState(
    resources: Resources = LocalContext.current.resources,
    navController: NavController
) = remember {
    MealsStateHolder(resources, navController)
}

@Composable
fun Meals(navController: NavController, viewModel: MealsViewModel = viewModel()){

    val stateHolder = rememberMealsState(navController = navController)

    Scaffold(topBar = {
        TopAppBar(elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onSecondary,
            title = {
                Text(text = "Meals")
            },
            actions = {
                Box {
                    IconButton(onClick = { stateHolder.expanded = true }) {
                        Icon(painter = painterResource(id = R.drawable.ic_more_vert_24), contentDescription = "Top bar menu")
                    }
                    DropdownMenu(
                        modifier = Modifier.background(color = MaterialTheme.colors.background),
                        expanded = stateHolder.expanded,
                        onDismissRequest = { stateHolder.expanded = false }) {
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Text(text = "Add meal", color = MaterialTheme.colors.surface)
                        }
                    }
                }
            },
            modifier = Modifier.padding(end = 5.dp))
    },
        floatingActionButton = {
                               FloatingActionButton(
                                   shape = RoundedCornerShape(50),
                                   backgroundColor = healthierGreen,
                                   contentColor = Color.White,
                                   onClick = { /*TODO*/ }
                               ) {
                                   Icon(Icons.Filled.Add, contentDescription = "add meal")
                               }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false
    ) {
        LazyColumn(contentPadding = PaddingValues(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier.fillMaxWidth()
        ){
            items(10){
                MealCard(
                    name = "Name",
                    image = "",
                    prepTime = "10 minutes",
                    creationDate = "14/5/2022",
                    cals = "100 kcal"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MealsPreview(){
    Meals(navController = rememberNavController(), viewModel = MealsViewModel())
}