package com.nexus.cleantodos.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nexus.cleantodos.feature_todos.data.dao.TodoDao
import com.nexus.cleantodos.feature_todos.domain.models.Todo


@Database(
    entities = [Todo::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        val DATABASE_NAME = "TODOS_DB"
    }
}