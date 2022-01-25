package com.healthier.healthier.presentation.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.healthier.healthier.ui.theme.healthierGray
import com.healthier.healthier.ui.theme.healthierGreen

@Composable
fun Home(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {

        Text(text = "Home", fontSize = 28.sp)

        Spacer(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth())

        HomeCard()

        Spacer(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth())

        Text(text = "Notifications", fontSize = 20.sp)

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
private fun HomeCard(workouts: Int = 0, meals: Int = 0){
    Card(backgroundColor = healthierGreen, elevation = 4.dp, shape = MaterialTheme.shapes.medium) {
        Column(modifier = Modifier.padding(11.dp)) {
            Column(modifier = Modifier.padding(5.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                Text(text = "Workouts", fontSize = 28.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Text(text = "$workouts", fontSize = 38.sp, color = Color.White, fontWeight = FontWeight.Normal)
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White)
                .clip(
                    RoundedCornerShape(5.dp)
                ))

            Column(modifier = Modifier.padding(5.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                Text(text = "Meals", fontSize = 28.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Text(text = "$meals", fontSize = 38.sp, color = Color.White, fontWeight = FontWeight.Normal)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview(){
    val navController = rememberNavController()
    Home(navController = navController)
}