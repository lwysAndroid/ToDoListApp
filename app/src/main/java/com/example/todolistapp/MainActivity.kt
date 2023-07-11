package com.example.todolistapp

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todolistapp.features.createnote.CreateNoteFragment
import com.example.todolistapp.features.seenotes.SeeNotesFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var fragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        fragmentContainer = findViewById(R.id.fragmentContainer)
        goToListNote()
    }

    private fun goToListNote() {
        SeeNotesFragment.newInstance().apply {
                setDoOnClickItem(::goToUpdateNote)
                setDoOnClickAddNote(::goToCreateNote)
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, this)
                    .commit()
            }

    }

    private fun goToCreateNote() {
        replaceFragment(CreateNoteFragment.newInstance())
    }

    private fun goToUpdateNote(itemId: Int) {
        replaceFragment(CreateNoteFragment.newInstance(noteId = itemId))
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null).commit()
    }

}