package com.example.todolistapp.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.todolistapp.core.database.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(note: NoteEntity):Long

    @Query(
        value = """
            DELETE FROM note
            WHERE id in (:id)
        """
    )
    suspend fun deleteNote(id: Int)

    @Query(value = """SELECT * FROM note ORDER BY id""")
    fun getNotesOrderById(): Flow<List<NoteEntity>>

    @Query(value = """SELECT * FROM note ORDER BY title""")
    fun getNotesOrderByTitle(): Flow<List<NoteEntity>>

    @Query(value = """SELECT * FROM note ORDER BY message""")
    fun getNotesOrderByMessage(): Flow<List<NoteEntity>>

    @Query(value = """SELECT * FROM note WHERE id = :id""")
    fun getNoteById(id: Int): Flow<NoteEntity>

}