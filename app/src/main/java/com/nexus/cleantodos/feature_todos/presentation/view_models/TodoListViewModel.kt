package com.nexus.cleantodos.feature_todos.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexus.cleantodos.feature_todos.domain.exceptions.InvalidTodoException
import com.nexus.cleantodos.feature_todos.domain.models.Todo
import com.nexus.cleantodos.feature_todos.domain.use_cases.TodosUseCase
import com.nexus.cleantodos.feature_todos.presentation.screens.todo_list.TodoListUserEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    val todosUseCase: TodosUseCase
) : ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    private val _eventFlow = MutableSharedFlow<ResponseEvent>()
    val eventFlow : SharedFlow<ResponseEvent> = _eventFlow

    private var _lastRemovedTodo: Todo? = null

    init {
        getTodos()
    }


    fun onEvent(event: TodoListUserEvents) {
        when(event) {
            is TodoListUserEvents.AddToFavorites -> {
                addToFavorites(event.todo)
            }
            is TodoListUserEvents.UpdateCompletionStatus -> {
                updateCompletionStatus(event.todo, event.newStatus)
            }
            is TodoListUserEvents.UndoDeletion -> {
                restoreTodo()
            }
            is TodoListUserEvents.DeleteTodo -> {
                deleteTodo(event.todo)
            }
        }
    }

    private fun getTodos() {
        viewModelScope.launch {
            todosUseCase.getTodosUseCase().onEach { todos ->
                _todos.value = todos
            }
        }
    }

    private fun addToFavorites(todo: Todo) {
        viewModelScope.launch {
            try {
                val updatedTodo = todo.copy(completed = true)
                todosUseCase.addTodoUseCase(updatedTodo)
                _eventFlow.emit(ResponseEvent.ShowSnackbar("Added to favourites"))
                getTodos()
            } catch (e: InvalidTodoException) {
                _eventFlow.emit(ResponseEvent.ShowSnackbar(e.message!!, isError = true))
            }
        }
    }

    private fun updateCompletionStatus(todo: Todo, newStatus: Boolean) {
        viewModelScope.launch {
            try {
                val updatedTodo = todo.copy(completed = newStatus)
                todosUseCase.addTodoUseCase(updatedTodo)
                _eventFlow.emit(ResponseEvent.ShowSnackbar("Status updated"))
                getTodos()
            } catch (e: InvalidTodoException) {
                _eventFlow.emit(ResponseEvent.ShowSnackbar(e.message!!, isError = true))
            }
        }
    }

    private fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todosUseCase.deleteTodoUseCase(todo)
            _lastRemovedTodo = todo
            _eventFlow.emit(
                ResponseEvent.ShowSnackbar("Todo Deleted", action = "Restore")
            )
        }
    }

    private fun restoreTodo() {
        _lastRemovedTodo?.let {
            viewModelScope.launch {
                try {
                    todosUseCase.addTodoUseCase(it)
                    getTodos()
                    _eventFlow.emit(ResponseEvent.ShowSnackbar("Todo restored"))
                } catch (e: InvalidTodoException) {
                    _eventFlow.emit(ResponseEvent.ShowSnackbar(e.message!!, isError = true))
                }
            }
        }

    }

    sealed class ResponseEvent {
        data class ShowSnackbar(val message: String,
                                val isError: Boolean = false,
                                val action: String? = null
        ) : ResponseEvent()

    }
}