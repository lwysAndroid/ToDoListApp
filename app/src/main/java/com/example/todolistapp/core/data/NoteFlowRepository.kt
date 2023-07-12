package com.example.todolistapp.core.data

import com.example.todolistapp.core.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteFlowRepository {

    suspend fun save(note: NoteModel):Int
    suspend fun update(note: NoteModel):Int
    suspend fun delete(noteId: Int)
    fun getAllFlow(): Flow<List<NoteModel>>
    suspend fun getNoteById(noteId: Int): Flow<NoteModel?>

}