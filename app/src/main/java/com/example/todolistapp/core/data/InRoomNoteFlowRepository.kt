package com.example.todolistapp.core.data

import com.example.todolistapp.core.database.dao.NoteDao
import com.example.todolistapp.core.database.model.NoteEntity
import com.example.todolistapp.core.database.model.toNoteModel
import com.example.todolistapp.core.model.NoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InRoomNoteFlowRepository @Inject constructor(
    private val noteDao: NoteDao,
) : NoteFlowRepository {

    override suspend fun save(note: NoteModel): Int {
       return noteDao.upsertNote(note = NoteEntity.getFromNoteModel(note)).toInt()
    }

    override suspend fun update(note: NoteModel): Int {
        return noteDao.upsertNote(note = NoteEntity.getFromNoteModel(note)).toInt()
    }

    override suspend fun delete(noteId: Int) {
        noteDao.deleteNote(id = noteId)
    }

    override fun getAllFlow(): Flow<List<NoteModel>> {
        return noteDao.getNotesOrderById()
            .map { list -> list.map { it.toNoteModel() } }
    }

    override suspend fun getNoteById(noteId: Int): Flow<NoteModel> {
        return noteDao.getNoteById(id = noteId).map { it.toNoteModel() }
    }

}