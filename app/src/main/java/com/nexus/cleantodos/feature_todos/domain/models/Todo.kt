package com.nexus.cleantodos.feature_todos.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title: String,
    val description: String?,
    val completed: Boolean = false,
    val secured: Boolean = false,
    val isFavorite: Boolean = false,

    @PrimaryKey val id: Int? = null,
)
