package com.example.todolistapp.features.createnote

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todolistapp.R
import com.example.todolistapp.core.model.NoteModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateNoteFragment : Fragment() {
    companion object {
        private const val NOTE_ID = "NOTE_ID"
        fun newInstance(noteId: Int = NoteModel.DEFAULT_ID): CreateNoteFragment {
            val args = Bundle()
            args.putInt(NOTE_ID, noteId)
            return CreateNoteFragment().apply { arguments = args }
        }
    }


    private val viewModel by viewModels<CreateNoteViewModel>()
    private lateinit var noteTitleEt: EditText
    private lateinit var noteMessageEt: EditText
    private lateinit var backBtn: Button
    private lateinit var saveNoteBtn: Button
    private lateinit var deleteNoteBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(NOTE_ID)?.also { noteId -> viewModel.setNoteId(noteId) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backBtn = view.findViewById(R.id.backBtn)
        noteTitleEt = view.findViewById(R.id.noteTitleEt)
        noteMessageEt = view.findViewById(R.id.noteMessageEt)
        saveNoteBtn = view.findViewById(R.id.saveNoteBtn)
        deleteNoteBtn = view.findViewById(R.id.deleteNoteBtn)

        showKeyboard(noteTitleEt)
        viewModel.loadNote()

        viewModel.noteUnderReview.observe(viewLifecycleOwner) { currentNote ->
            noteTitleEt.setText(currentNote.title)
            noteMessageEt.setText(currentNote.message)
            showKeyboard(noteTitleEt)
        }

        viewModel.invalidNoteFormat.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.noteCreated.observe(viewLifecycleOwner) {
            Toast.makeText(activity, "The note was created id:$it", Toast.LENGTH_SHORT).show()
            goToPreviousScreen()
        }

        viewModel.noteDeleted.observe(viewLifecycleOwner) {
            noteTitleEt.setText("")
            noteMessageEt.setText("")
            Toast.makeText(activity, "The note was Deleted", Toast.LENGTH_SHORT).show()
            goToPreviousScreen()
        }

        viewModel.emptyNoteNoDeleted.observe(viewLifecycleOwner) {
            Toast.makeText(activity, "There isn't any note to be Deleted", Toast.LENGTH_SHORT)
                .show()
        }

        backBtn.setOnClickListener { goToPreviousScreen() }
        saveNoteBtn.setOnClickListener { saveNote() }
        deleteNoteBtn.setOnClickListener { showDialogToDeleteNote() }
    }

    private fun saveNote() {
        val title = noteTitleEt.text.toString()
        val message = noteMessageEt.text.toString()
        viewModel.saveNote(title = title, message = message)
    }

    private fun showDialogToDeleteNote() {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.delete_note)
                .setMessage(R.string.ask_to_delete_note)
                .setPositiveButton(R.string.yes) { _, _ -> deleteNote() }
                .setNegativeButton(R.string.no, null)
                .setIcon(R.drawable.baseline_question_mark)
                .show()
        }
    }

    private fun deleteNote() {
        viewModel.deleteNote()
    }

    private fun hideKeyboard() {
        activity?.let { _activity ->
            val imm =
                _activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            view?.let { imm.hideSoftInputFromWindow(it.windowToken, 0) }
        }
    }

    private fun showKeyboard(currentEditText: EditText) {
        currentEditText.requestFocus()
        currentEditText.setSelection(currentEditText.length())
        activity?.let { _activity ->
            val imm =
                _activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            view?.let { imm.showSoftInput(currentEditText, 0) }
        }
    }

    private fun goToPreviousScreen() {
        hideKeyboard()
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

}