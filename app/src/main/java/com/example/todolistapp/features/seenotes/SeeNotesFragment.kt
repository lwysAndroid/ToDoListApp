package com.example.todolistapp.features.seenotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeNotesFragment : Fragment() {

    companion object {
        fun newInstance() = SeeNotesFragment()
    }

    private val viewModel by viewModels<SeeNotesViewModel>()
    private lateinit var noteRecyclerView: RecyclerView
    private val notesAdapter = NotesAdapter()
    private var doOnClickItem: (Int) -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_see_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteRecyclerView = view.findViewById(R.id.noteRecyclerView)
        setUpView()
    }

    private fun setUpView() {
        notesAdapter.setListener(doOnClickItem)
        noteRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = notesAdapter
        }
        viewModel.notes.observe(viewLifecycleOwner) { notesList ->
            notesAdapter.updateList(list = notesList)
        }
    }

    fun setDoOnClickItem(listener: (Int) -> Unit) {
        doOnClickItem = listener
    }

}