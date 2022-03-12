package com.nexus.cleantodos.feature_todos.domain.use_cases

import com.nexus.cleantodos.feature_todos.domain.models.Todo
import com.nexus.cleantodos.feature_todos.domain.repository.TodoRepository

class GetTodoByIdUseCase(
    val todoRepository: TodoRepository
) {

    suspend operator fun invoke(id: Int) : Todo? {
        return todoRepository.getTodoById(id)
    }
}