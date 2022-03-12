package com.nexus.cleantodos.feature_todos.domain.use_cases

import com.nexus.cleantodos.feature_todos.domain.models.Todo
import com.nexus.cleantodos.feature_todos.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(
    val todoRepository: TodoRepository
) {
    operator fun invoke(): Flow<List<Todo>> {
        return todoRepository.getFavoriteTodos()
    }
}