package com.nexus.cleantodos.feature_todos.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.nexus.cleantodos.feature_todos.domain.models.Todo

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoItemComponent(
    todo: Todo,
    modifier: Modifier = Modifier,
    onTodoClick: () -> Unit,
    onContextMenuClick: () -> Unit,
) {

    ListItem(
        modifier = modifier.clickable {
            onTodoClick()
        },
        text = {
            Text(text = todo.title)
        },
        trailing = {
            IconButton(onClick = onContextMenuClick) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Context menu")
            }
        },
        singleLineSecondaryText = false,
        secondaryText = {
            todo.description?.let {
                Text(text = it)
            }
        }
    )
}