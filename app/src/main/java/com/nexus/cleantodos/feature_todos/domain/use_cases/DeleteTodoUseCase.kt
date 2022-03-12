package com.nexus.cleantodos.feature_todos.domain.use_cases

import com.nexus.cleantodos.feature_todos.domain.models.Todo
import com.nexus.cleantodos.feature_todos.domain.repository.TodoRepository

class DeleteTodoUseCase(
    val todoRepository: TodoRepository
) {

    suspend operator fun invoke(todo: Todo) {
        todoRepository.deleteTodo(todo)
    }
}