package com.example.todolistapp

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todolistapp.features.createnote.CreateNoteFragment
import com.example.todolistapp.features.seenotes.SeeNotesFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var fragmentContainer: FrameLayout
    private lateinit var addNoteBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        fragmentContainer = findViewById(R.id.fragmentContainer)
        addNoteBtn = findViewById(R.id.addNoteBtn)
        goToListNote()
        addNoteBtn.setOnClickListener { goToCreateNote() }
    }


    private fun goToListNote() {
        replaceFragment(SeeNotesFragment.newInstance())
    }

    private fun goToCreateNote() {
        replaceFragment(CreateNoteFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null).commit()
    }

}