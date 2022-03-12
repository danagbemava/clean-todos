package com.nexus.cleantodos.feature_todos.presentation.screens.todo_list

import com.nexus.cleantodos.feature_todos.domain.models.Todo

sealed class TodoListUserEvents {
    data class AddToFavorites(val todo: Todo) : TodoListUserEvents()
    data class UpdateCompletionStatus(val todo: Todo, val newStatus: Boolean) : TodoListUserEvents()
    data class DeleteTodo(val todo: Todo) : TodoListUserEvents()
    object UndoDeletion : TodoListUserEvents()
}
