package com.example.todolistapp.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolistapp.core.model.NoteModel

@Entity(
    tableName = "note",
)
data class NoteEntity(
    val title: String,
    val message: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
) {
    companion object {
        fun getFromNoteModel(noteModel: NoteModel): NoteEntity {
            return with(noteModel) {
                val noteId = if (noteModel.id == NoteModel.DEFAULT_ID) {
                    null
                } else {
                    noteModel.id
                }
                NoteEntity(id = noteId, title = title, message = message)
            }
        }
    }
}

fun NoteEntity.toNoteModel(): NoteModel {
    return NoteModel(id = id ?: NoteModel.DEFAULT_ID, title = title, message = message)
}

