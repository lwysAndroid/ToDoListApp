package com.example.todolistapp.features.seenotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.core.model.NoteModel

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val notesList = mutableListOf<NoteModel>()
    private var doOnClickItem: (Int) -> Unit = {}

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cellNoteContainer: View
        val noteTitleTv: TextView
        val noteMessageTv: TextView
        private var doOnClickContainer: () -> Unit = {}

        init {
            cellNoteContainer = view.findViewById(R.id.cellNoteContainer)
            noteTitleTv = view.findViewById(R.id.noteTitleTv)
            noteMessageTv = view.findViewById(R.id.noteMessageTv)

            cellNoteContainer.setOnClickListener { doOnClickContainer.invoke() }
        }

        fun setListener(listener: () -> Unit) {
            doOnClickContainer = listener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_cell, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = notesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.noteTitleTv.text = currentNote.title
        holder.noteMessageTv.text = currentNote.message
        holder.setListener { doOnClickItem.invoke(currentNote.id) }
    }

    fun updateList(list: List<NoteModel>) {
        notesList.clear()
        notesList.addAll(list)
        notifyDataSetChanged()
    }

    fun setListener(listener: (Int) -> Unit) {
        doOnClickItem = listener
    }

}