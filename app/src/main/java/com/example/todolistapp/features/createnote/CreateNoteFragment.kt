package com.example.todolistapp.features.createnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.todolistapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNoteFragment : Fragment() {

    companion object {
        fun newInstance() = CreateNoteFragment()
    }

    private val viewModel by viewModels<CreateNoteViewModel>()
    private lateinit var noteTitleEt: EditText
    private lateinit var noteMessageEt: EditText
    private lateinit var saveNoteBtn: Button
    private lateinit var deleteNoteBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteTitleEt = view.findViewById(R.id.noteTitleEt)
        noteMessageEt = view.findViewById(R.id.noteMessageEt)
        saveNoteBtn = view.findViewById(R.id.saveNoteBtn)
        deleteNoteBtn = view.findViewById(R.id.deleteNoteBtn)

        viewModel.invalidNoteFormat.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.noteCreated.observe(viewLifecycleOwner) {
            Toast.makeText(activity, "The note was created id:$it", Toast.LENGTH_SHORT).show()
        }

        viewModel.noteDeleted.observe(viewLifecycleOwner) {
            noteTitleEt.setText("")
            noteMessageEt.setText("")
            Toast.makeText(activity, "The note was Deleted", Toast.LENGTH_SHORT).show()
        }

        viewModel.emptyNoteNoDeleted.observe(viewLifecycleOwner) {
            Toast.makeText(activity, "There isn't any note to be Deleted", Toast.LENGTH_SHORT).show()
        }

        saveNoteBtn.setOnClickListener { saveNote() }
        deleteNoteBtn.setOnClickListener { deleteNote() }
    }

    private fun saveNote() {
        val title = noteTitleEt.text.toString()
        val message = noteMessageEt.text.toString()
        viewModel.saveNote(title = title, message = message)
    }

    private fun deleteNote() {
        viewModel.deleteNote()
    }

}