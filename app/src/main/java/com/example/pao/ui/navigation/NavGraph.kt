package com.example.pao.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pao.ui.screens.*
import com.example.pao.ui.theme.*

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Teach : Screen("teach", "Teach", Icons.Filled.MenuBook)
    object Flashcards : Screen("flashcards", "Cards", Icons.Filled.Style)
    object Quiz : Screen("quiz", "Quiz", Icons.Filled.Quiz)
    object Table : Screen("table", "Table", Icons.Filled.List)
    object HowTo : Screen("howto", "How-To", Icons.Filled.Info)
}

@Composable
fun PAONavGraph() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Teach,
        Screen.Flashcards,
        Screen.Quiz,
        Screen.Table,
        Screen.HowTo
    )

    Scaffold(
        containerColor = DarkBg,
        bottomBar = {
            NavigationBar(containerColor = DarkSurface) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label, tint = if (currentRoute == screen.route) Orange else LightGrey) },
                        label = { Text(screen.label, fontSize = androidx.compose.ui.unit.TextUnit.Unspecified, color = if (currentRoute == screen.route) Orange else LightGrey) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color(0xFF2A2A2A)
                        )
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Teach.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Teach.route) { TeachScreen() }
            composable(Screen.Flashcards.route) { FlashcardsScreen() }
            composable(Screen.Quiz.route) { QuizScreen() }
            composable(Screen.Table.route) { TableScreen() }
            composable(Screen.HowTo.route) { HowToScreen() }
        }
    }
}
