package com.healthier.healthier.presentation.screens.main.fitness

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController


@Composable
fun Meals(navController: NavController, viewModel: MealsViewModel = viewModel()){

}


@Preview(showBackground = true)
@Composable
fun MealsPreview(){
    Meals(navController = rememberNavController(), viewModel = MealsViewModel())
}