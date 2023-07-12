package com.example.todolistapp.features.createnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.core.data.NoteFlowRepository
import com.example.todolistapp.core.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val noteFlowRepository: NoteFlowRepository
) : ViewModel() {

    private var noteId = NoteModel.DEFAULT_ID

    private val _invalidNoteFormat: MutableLiveData<String> = MutableLiveData()
    val invalidNoteFormat: LiveData<String> = _invalidNoteFormat

    private val _noteCreated: MutableLiveData<Int> = MutableLiveData()
    val noteCreated: LiveData<Int> = _noteCreated

    private val _noteDeleted: MutableLiveData<Unit> = MutableLiveData()
    val noteDeleted: LiveData<Unit> = _noteDeleted

    private val _emptyNoteNoDeleted: MutableLiveData<Unit> = MutableLiveData()
    val emptyNoteNoDeleted: LiveData<Unit> = _emptyNoteNoDeleted

    private val _noteUnderReview: MutableLiveData<NoteModel> = MutableLiveData()
    val noteUnderReview: LiveData<NoteModel> = _noteUnderReview

    fun setNoteId(noteId: Int) {
        this.noteId = noteId
    }

    fun saveNote(title: String, message: String) {
        if (title.isBlank() || message.isBlank()) {
            _invalidNoteFormat.value = "The title and the message can't be empty"
        } else {
            viewModelScope.launch {
                val newNote = NoteModel(id = noteId, title = title, message = message)
                noteFlowRepository.save(newNote).also {
                    noteId = it
                    _noteCreated.value = it
                }
            }
        }
    }

    fun deleteNote() {
        if (noteId != NoteModel.DEFAULT_ID) {
            viewModelScope.launch {
                noteFlowRepository.delete(noteId = noteId).also {
                    noteId = NoteModel.DEFAULT_ID
                    _noteDeleted.value = it
                }
            }
        } else {
            _emptyNoteNoDeleted.value = Unit
        }
    }

    fun loadNote() {
        if (noteId != NoteModel.DEFAULT_ID) {
            viewModelScope.launch {
                noteFlowRepository.getNoteById(noteId = noteId).collect() {
                    _noteUnderReview.value = it
                }
            }
        }
    }

}