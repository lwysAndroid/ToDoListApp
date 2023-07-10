package com.example.todolistapp.features.seenotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolistapp.R

class SeeNotesFragment : Fragment() {

    companion object {
        fun newInstance() = SeeNotesFragment()
    }

    private lateinit var viewModel: SeeNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_see_notes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SeeNotesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}