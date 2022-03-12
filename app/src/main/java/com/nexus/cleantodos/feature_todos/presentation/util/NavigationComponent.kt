package com.nexus.cleantodos.feature_todos.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nexus.cleantodos.feature_todos.presentation.screens.todo_list.TodoListScreen

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Navigation.TodoListScreen.route) {
        composable(Navigation.TodoListScreen.route) {
            TodoListScreen(navController = navController)
        }
    }
}