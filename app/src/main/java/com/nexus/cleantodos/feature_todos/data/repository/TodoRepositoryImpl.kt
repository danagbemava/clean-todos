package com.nexus.cleantodos.feature_todos.data.repository

import com.nexus.cleantodos.feature_todos.data.dao.TodoDao
import com.nexus.cleantodos.feature_todos.domain.models.Todo
import com.nexus.cleantodos.feature_todos.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    val dao: TodoDao
) : TodoRepository {
    override suspend fun saveTodo(todo: Todo) {
        dao.saveTodo(todo)
    }

    override fun getAllTodos(): Flow<List<Todo>> {
        return dao.getAllTodos()
    }

    override fun getFavoriteTodos(): Flow<List<Todo>> {
       return dao.getFavoriteTodos()
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }
}