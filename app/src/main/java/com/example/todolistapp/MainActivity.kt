package com.example.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var showAllNotesBtn: Button
    private lateinit var showCreateNoteBtn: Button

    private lateinit var seeNotesContainer: View
    private lateinit var createNoteContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        seeNotesContainer = findViewById(R.id.seeNotesContainer)
        createNoteContainer = findViewById(R.id.createNoteContainer)
        showAllNotesBtn = findViewById(R.id.showAllNotesBtn)
        showCreateNoteBtn = findViewById(R.id.showCreateNoteBtn)

        showAllNotesBtn.setOnClickListener { showAllNotes() }
        showCreateNoteBtn.setOnClickListener { showCreateNote() }
    }

    private fun showAllNotes() {
        seeNotesContainer.visibility = View.VISIBLE
        createNoteContainer.visibility = View.GONE
    }

    private fun showCreateNote() {
        seeNotesContainer.visibility = View.GONE
        createNoteContainer.visibility = View.VISIBLE
    }

}