package com.example.todolistapp.core.data

import com.example.todolistapp.core.model.NoteModel
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class InMemoryNoteRepositoryTest {

    lateinit var inMemoryNoteRepository: NoteRepository

    @Before
    fun setUp() {
        inMemoryNoteRepository = InMemoryNoteRepository()
    }

    @Test
    fun save() {
        val title = "Test Title"
        val message = "Test Message"
        NoteModel(title = title, message = message).also { inMemoryNoteRepository.save(it) }
        inMemoryNoteRepository.getNoteById(noteId = 1).also {
            assertEquals(title, it!!.title)
            assertEquals(message, it.message)
        }

        val title2 = "Test Title 2"
        val message2 = "Test Message 2"
        NoteModel(title = title2, message = message2).also { inMemoryNoteRepository.save(it) }
        inMemoryNoteRepository.getNoteById(noteId = 2).also {
            assertEquals(title2, it!!.title)
            assertEquals(message2, it.message)
        }

    }

    @Test
    fun update() {
        val title = "Test Title"
        val message = "Test Message"
        NoteModel(title = title, message = message).also { inMemoryNoteRepository.save(it) }

        val title2 = "Test Title 2"
        inMemoryNoteRepository.getNoteById(noteId = 1)?.copy(title = title2)
            .also { it?.let { it1 -> inMemoryNoteRepository.save(it1) } }
        inMemoryNoteRepository.getNoteById(noteId = 1).also {
            assertEquals(title2, it!!.title)
            assertEquals(message, it.message)
        }
    }

    @Test
    fun delete() {
        val title = "Test Title"
        val message = "Test Message"
        NoteModel(title = title, message = message).also { inMemoryNoteRepository.save(it) }

        val title2 = "Test Title 2"
        val message2 = "Test Message 2"
        NoteModel(title = title2, message = message2).also { inMemoryNoteRepository.save(it) }
        inMemoryNoteRepository.delete(1)
        inMemoryNoteRepository.getAll()[0].also {
            assertEquals(title2, it.title)
            assertEquals(message2, it.message)
        }

    }

    @Test
    fun getAll() {
        val title = "Test Title"
        val message = "Test Message"
        NoteModel(title = title, message = message).also { inMemoryNoteRepository.save(it) }

        val title2 = "Test Title 2"
        val message2 = "Test Message 2"
        NoteModel(title = title2, message = message2).also { inMemoryNoteRepository.save(it) }

        val listNotes = inMemoryNoteRepository.getAll()
        assertEquals(2, listNotes.size)
        listNotes[1].also {
            assertEquals(title2, it.title)
            assertEquals(message2, it.message)
        }
    }

    @Test
    fun getNoteById() {
        val title = "Test Title"
        val message = "Test Message"
        NoteModel(title = title, message = message).also { inMemoryNoteRepository.save(it) }

        val title2 = "Test Title 2"
        val message2 = "Test Message 2"
        NoteModel(title = title2, message = message2).also { inMemoryNoteRepository.save(it) }

        inMemoryNoteRepository.getNoteById(noteId = 2).also {
            assertEquals(title2, it!!.title)
            assertEquals(message2, it.message)
        }
    }
}