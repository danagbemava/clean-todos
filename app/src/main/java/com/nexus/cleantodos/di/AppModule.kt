package com.nexus.cleantodos.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nexus.cleantodos.core.database.AppDatabase
import com.nexus.cleantodos.feature_todos.data.repository.TodoRepositoryImpl
import com.nexus.cleantodos.feature_todos.domain.repository.TodoRepository
import com.nexus.cleantodos.feature_todos.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodosRepository(db: AppDatabase) : TodoRepository {
        return TodoRepositoryImpl(db.todoDao())
    }

    @Provides
    @Singleton
    fun provideTodosUseCase(repo: TodoRepository) : TodosUseCase {
        return TodosUseCase(
            addTodoUseCase = AddTodoUseCase(repo),
            deleteTodoUseCase = DeleteTodoUseCase(repo),
            getFavoritesUseCase = GetFavoritesUseCase(repo),
            getTodoByIdUseCase = GetTodoByIdUseCase(repo),
            getTodosUseCase = GetTodosUseCase(repo)
        )
    }
}