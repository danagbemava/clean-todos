package com.nexus.cleantodos.feature_todos.domain.repository

import com.nexus.cleantodos.feature_todos.domain.models.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    // CREATE/UPDATE Operations
    suspend fun saveTodo(todo: Todo)

    // READ Operations
    fun getAllTodos() : Flow<List<Todo>>
    fun getFavoriteTodos(): Flow<List<Todo>>
    suspend fun getTodoById(id: Int) : Todo?

    // DELETE Operations
    suspend fun deleteTodo(todo: Todo)
}