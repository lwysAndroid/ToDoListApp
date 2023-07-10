package com.example.todolistapp.core.data

import com.example.todolistapp.core.model.NoteModel

interface NoteRepository {

    fun save(note: NoteModel)
    fun update(note: NoteModel)
    fun delete(noteId: Int)
    fun getAll(): List<NoteModel>
    fun getNoteById(noteId: Int): NoteModel?

}