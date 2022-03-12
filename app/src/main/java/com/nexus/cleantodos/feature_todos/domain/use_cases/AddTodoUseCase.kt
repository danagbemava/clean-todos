package com.nexus.cleantodos.feature_todos.domain.use_cases

import com.nexus.cleantodos.feature_todos.domain.exceptions.InvalidTodoException
import com.nexus.cleantodos.feature_todos.domain.models.Todo
import com.nexus.cleantodos.feature_todos.domain.repository.TodoRepository
import kotlin.jvm.Throws

class AddTodoUseCase(
    val todoRepository: TodoRepository
) {

    @Throws(InvalidTodoException::class)
     suspend operator fun invoke(todo: Todo) {
         if (todo.title.isEmpty()) {
            throw InvalidTodoException("Title is required")
         }

        todoRepository.saveTodo(todo)
     }
}