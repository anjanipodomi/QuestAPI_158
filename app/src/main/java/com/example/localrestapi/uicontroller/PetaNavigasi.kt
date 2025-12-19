package com.example.localrestapi.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.localrestapi.uicontroller.route.DestinasiEntry
import com.example.localrestapi.uicontroller.route.DestinasiHome
import com.example.localrestapi.view.EntrySiswaScreen
import com.example.localrestapi.view.HomeScreen
//import com.example.localrestapi.uicontroller.route.DestinasiDetail
import androidx.navigation.compose.rememberNavController



@Composable
fun DataSiswaApp(navController: NavHostController = rememberNavController(),
                 modifier: Modifier){
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                navigateToItemUpdate = { //id ->
                    //navController.navigate("${DestinasiDetail.route}/$id")
                }
            )
        }
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route)
                })
        }
    }
}

@Composable
fun DataSiswaApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    HostNavigasi(
        navController = navController,
        modifier = modifier
    )
}
