package com.example.todolistapp.features.createnote

import androidx.lifecycle.ViewModel
import com.example.todolistapp.core.data.InMemoryNoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val inMemoryNoteRepository: InMemoryNoteRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
}