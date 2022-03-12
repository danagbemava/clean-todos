package com.nexus.cleantodos.feature_todos.presentation.util

sealed class Navigation(val route: String) {
    object TodoListScreen: Navigation("todo_list")
    object AddEditTodoScreen : Navigation("add_edit_todos")
    object FavoritesScreen : Navigation("favorite_todos")
    object SettingsScreen : Navigation("settings")
    object PrivateTodosRoute : Navigation("private_todos")
}
