package com.healthier.healthier.presentation.screens.main.fitness

import android.content.res.Resources
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.healthier.healthier.R
import com.healthier.healthier.presentation.common.WorkOutCard
import com.healthier.healthier.ui.theme.healthierGreen

@Composable
fun rememberFitnessState(
    resources: Resources = LocalContext.current.resources,
    navController: NavController
) = remember {
    FitnessStateHolder(resources = resources, navController = navController)
}

@Composable
fun Fitness(navController: NavController, viewModel: FitnessViewModel = viewModel()){

    val stateHolder = rememberFitnessState(navController = navController)

    Scaffold(topBar = {
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onSecondary,
            title = {
                Text(text = "Workouts")
            },
            actions = {
                Box {
                    IconButton(onClick = { stateHolder.expanded = true }) {
                        Icon(painter = painterResource(id = R.drawable.ic_more_vert_24), contentDescription = "Top bar menu")
                    }
                    DropdownMenu(
                        expanded = stateHolder.expanded,
                        onDismissRequest = { stateHolder.expanded = false }) {
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Text(text = "Add workout", color = MaterialTheme.colors.surface)
                        }
                    }
                }
            },
            modifier = Modifier.padding(end = 5.dp)
        )
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
        LazyColumn(contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)){

            items(10){
                WorkOutCard(name = "name", weight = "10kg", sets = "3", reps = "10")
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun FitnessPreview(){

    Fitness(navController = rememberNavController(), viewModel = FitnessViewModel())
}