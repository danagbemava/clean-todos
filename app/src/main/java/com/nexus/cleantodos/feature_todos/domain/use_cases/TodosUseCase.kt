package com.nexus.cleantodos.feature_todos.domain.use_cases

data class TodosUseCase(
    val addTodoUseCase: AddTodoUseCase,
    val getFavoritesUseCase: GetFavoritesUseCase,
    val getTodosUseCase: GetTodosUseCase,
    val deleteTodoUseCase: DeleteTodoUseCase,
    val getTodoByIdUseCase: GetTodoByIdUseCase
)