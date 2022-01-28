package com.healthier.healthier.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.healthier.healthier.presentation.common.parentExitTransition
import com.healthier.healthier.presentation.common.parentPopEnterTransition
import com.healthier.healthier.presentation.navigation.Screen
import com.healthier.healthier.presentation.screens.main.account.Account
import com.healthier.healthier.presentation.screens.main.home.Home
import com.healthier.healthier.ui.theme.HealthierTheme

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screens = listOf(
            Screen.HomeScreen,
            Screen.FitnessScreen,
            Screen.MealsScreen,
            Screen.AccountScreen,
        )

        setContent {
            HealthierTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberAnimatedNavController()

                    Scaffold(bottomBar = {
                        BottomNavigation (backgroundColor = MaterialTheme.colors.background, elevation = 0.dp) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            screens.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(painter = painterResource(id = screen.icon), contentDescription = screen.route) },
                                    label = { Text(text = screen.name) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id){
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                    ) { innerPadding ->
                        AnimatedNavHost(navController, startDestination = Screen.HomeScreen.route, modifier = Modifier.padding(innerPadding)) {
                            composable(Screen.HomeScreen.route) {
                                Home(navController = navController)
                            }
                            composable(Screen.FitnessScreen.route) {

                            }
                            composable(Screen.MealsScreen.route) {

                            }
                            composable(Screen.AccountScreen.route) {
                                Account(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

