package com.example.todolistapp.core.data

import com.example.todolistapp.core.model.NoteModel

class InMemoryNoteRepository : NoteRepository {

    private val noteHasMap = hashMapOf<Int, NoteModel>()

    override fun save(note: NoteModel) {
        val currentNodeId = note.id
        if (noteHasMap.containsKey(currentNodeId)) {
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
        }
    }

    private fun generateId(): Int {
        if (noteHasMap.isEmpty()) {
            return 1
        }
        return noteHasMap.keys.maxOf { it } + 1
    }

    override fun update(note: NoteModel) {
        noteHasMap[note.id] = note
    }

    override fun delete(noteId: Int) {
        if (noteHasMap.containsKey(noteId)) {
            noteHasMap.remove(noteId)
        }
    }

    override fun getAll(): List<NoteModel> {
        return noteHasMap.values.toList().sortedBy { it.id }
    }

    override fun getNoteById(noteId: Int): NoteModel? {
        return if (noteHasMap.containsKey(noteId)) {
            noteHasMap[noteId]
        } else {
            null
        }
    }

}