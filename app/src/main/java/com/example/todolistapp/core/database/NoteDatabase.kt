package com.example.todolistapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistapp.core.database.dao.NoteDao
import com.example.todolistapp.core.database.model.NoteEntity

@Database(
    entities = [NoteEntity::class], version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}