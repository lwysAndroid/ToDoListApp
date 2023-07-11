package com.example.todolistapp.core.data

import com.example.todolistapp.core.model.NoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryNoteFlowRepository @Inject constructor() : NoteFlowRepository {

    private val noteHasMap = hashMapOf<Int, NoteModel>()

    private val flow = MutableSharedFlow<List<NoteModel>>()
    private suspend fun emit() = flow.emit(getAll())


    override suspend fun save(note: NoteModel): Int {
        val currentNodeId = note.id
        return if (noteHasMap.containsKey(currentNodeId)) {
            update(note)
        } else {
            val newId = if (currentNodeId == NoteModel.DEFAULT_ID) {
                generateId()
            } else {
                currentNodeId
            }
            note.copy(id = newId).also {
                noteHasMap[newId] = it
            }
            emit()
            newId
        }
    }

    private fun generateId(): Int {
        if (noteHasMap.isEmpty()) {
            return 1
        }
        return noteHasMap.keys.maxOf { it } + 1
    }

    override suspend fun update(note: NoteModel): Int {
        noteHasMap[note.id] = note
        emit()
        return note.id
    }

    override suspend fun delete(noteId: Int) {
        if (noteHasMap.containsKey(noteId)) {
            noteHasMap.remove(noteId)
            emit()
        }
    }

    private fun getAll(): List<NoteModel> {
        return noteHasMap.values.toList().sortedBy { it.id }
    }

    override fun getAllFlow(): Flow<List<NoteModel>> {
        return flow
    }

    override suspend fun getNoteById(noteId: Int): NoteModel? {
        return if (noteHasMap.containsKey(noteId)) {
            noteHasMap[noteId]
        } else {
            null
        }
    }

}