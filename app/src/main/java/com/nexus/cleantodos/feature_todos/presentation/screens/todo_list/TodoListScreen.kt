package com.nexus.cleantodos.feature_todos.presentation.screens.todo_list

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nexus.cleantodos.feature_todos.presentation.components.TodoItemComponent
import com.nexus.cleantodos.feature_todos.presentation.view_models.TodoListViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    navController: NavController,
    viewModel: TodoListViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.onEach { event ->
            when (event) {
                is TodoListViewModel.ResponseEvent.ShowSnackbar -> {
                    coroutineScope.launch {
                        val actionResult = scaffoldState.snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.action,
                        )

                        if (actionResult == SnackbarResult.ActionPerformed) {
                            event.action?.let {
                                if (it == "Restore") {
                                    viewModel.onEvent(TodoListUserEvents.UndoDeletion)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "My Todos")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "Private Todos")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Todo")
            }
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(viewModel.todos.value) { todo ->
                TodoItemComponent(
                    todo = todo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    onTodoClick = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(todo.toString())
                        }
                    }, onContextMenuClick = {

                    }
                )
            }
        }
    }
}