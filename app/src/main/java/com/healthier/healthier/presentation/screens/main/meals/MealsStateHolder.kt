package com.healthier.healthier.presentation.screens.main.meals

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

class MealsStateHolder(private val resources: Resources, val navController: NavController) {
    var expanded by mutableStateOf(false)
}