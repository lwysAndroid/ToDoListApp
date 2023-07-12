package com.example.todolistapp.core.database

import com.example.todolistapp.core.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesTopicsDao(
        database: NoteDatabase,
    ): NoteDao = database.noteDao()

}