package com.example.todolistapp.features.seenotes

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
class SeeNotesViewModel @Inject constructor(
    private val noteFlowRepository: NoteFlowRepository
) : ViewModel() {

    private val _notes: MutableLiveData<List<NoteModel>> = MutableLiveData()
    val notes: LiveData<List<NoteModel>> = _notes

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            noteFlowRepository.getAllFlow().collect() {
                _notes.value = it
            }
        }
    }

}