package com.example.todolistapp.core.model

data class NoteModel(
    val id: Int = DEFAULT_ID,
    val title: String,
    val message: String
) {
    companion object {
        const val DEFAULT_ID = -1
    }
}
