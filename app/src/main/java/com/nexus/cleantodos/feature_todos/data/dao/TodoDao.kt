package com.nexus.cleantodos.feature_todos.data.dao

import androidx.room.*
import com.nexus.cleantodos.feature_todos.domain.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    // CREATE/UPDATE Operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTodo(todo: Todo)

    // READ Operations
    @Query("select * from todo")
    fun getAllTodos() : Flow<List<Todo>>

    @Query("select * from todo where isFavorite")
    fun getFavoriteTodos(): Flow<List<Todo>>

    @Query("select * from todo where id = :id")
    suspend fun getTodoById(id: Int) : Todo?

    // DELETE Operations
    @Delete
    suspend fun deleteTodo(todo: Todo)
}